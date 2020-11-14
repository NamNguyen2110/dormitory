package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.common.utils.CommonUtils;
import com.web.assgiment.dormitory.common.validator.group.RegexContant;
import com.web.assgiment.dormitory.domain.entity.Room;
import com.web.assgiment.dormitory.domain.entity.Student;
import com.web.assgiment.dormitory.domain.dto.PageDto;
import com.web.assgiment.dormitory.domain.dto.StudentDto;
import com.web.assgiment.dormitory.domain.dto.respond.StudentRespondDto;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.mapper.ObjectMapperUtils;
import com.web.assgiment.dormitory.repository.RoomRepository;
import com.web.assgiment.dormitory.repository.StudentRepository;
import com.web.assgiment.dormitory.service.StudentService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.Optional;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    private final Map<String, Object> resultPage = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public StudentDto saveStudent(StudentRespondDto studentDto) throws UserValidateException, ParseException {
        Optional<Room> optional = roomRepository.findById(studentDto.getRoomId());
        if (optional.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        Student student = checkPattern(studentDto);
        checkNullOrEmpty(studentDto);
        checkExisted(studentDto);
        student.setRoom(optional.get());
        student.setStatus(1);
        studentRepository.save(student);
        StudentDto newStudent = ObjectMapperUtils.toDto(student, StudentDto.class);
        return newStudent;
    }

    @Override
    public Map<String, Object> getAllStudent(PageDto pageDto) throws UserValidateException {
        if (pageDto.getOffset() < 0 || pageDto.getLimit() < 0) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.target.object.page"));
        }
        Pageable pageable = PageRequest.of(pageDto.getOffset(), pageDto.getLimit(), Sort.by("studentCode"));
        Page<Student> studentPage = studentRepository.getAllStudent(pageable);
        customizePagination(studentPage);
        return resultPage;
    }

    @Override
    public List<StudentRespondDto> deleteStudent(List<Integer> id) throws UserValidateException {
        List<StudentRespondDto> dtos = new ArrayList<>();
        for (Integer studentId : id) {
            Optional<Student> optional = studentRepository.findById(studentId);
            if (optional.isEmpty()) {
                throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
            }
            if (optional.get().getStatus() == 0) {
                throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target.not.exist"));
            }
            optional.get().setStatus(0);
            studentRepository.save(optional.get());
            StudentRespondDto dto = ObjectMapperUtils.toDto(optional.get(), StudentRespondDto.class);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public StudentRespondDto updateStudent(StudentDto studentDto) throws UserValidateException, ParseException {
        Optional<Room> optionalRoom = roomRepository.findById(studentDto.getRoomId());
        if (optionalRoom.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        Optional<Student> optional = studentRepository.findById(studentDto.getStudentId());
        if (optional.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        if (optional.get().getStatus() == 0) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target.not.exist"));
        }
        checkNullOrEmpty(studentDto);
        checkPattern(studentDto);
        optional.get().setCardId(studentDto.getCardId());
        optional.get().setStudentCode(studentDto.getStudentCode());
        optional.get().setGrade(studentDto.getGrade());
        optional.get().setAddress(studentDto.getAddress());
        optional.get().setRoom(optionalRoom.get());
        studentRepository.save(optional.get());
        StudentRespondDto dto = ObjectMapperUtils.toDto(optional.get(), StudentRespondDto.class);
        return dto;
    }

    @Override
    public Map<String, Object> searchByStudentCode(PageDto pageDto, String studentCode) throws UserValidateException {
        Pageable pageable = PageRequest.of(pageDto.getOffset(), pageDto.getLimit(), Sort.by("studentCode"));
        Page<Student> studentPage = studentRepository.filterByCode(pageable, studentCode);
        customizePagination(studentPage);
        return resultPage;
    }


    private void customizePagination(Page<Student> page) {
        List<Student> roomList = page.getContent();
        List<StudentDto> dtos = ObjectMapperUtils.toDto(roomList, StudentDto.class);
        resultPage.put("data", dtos);
        resultPage.put("totalItems", page.getSize());
        resultPage.put("currentPage", page.getNumber());
        resultPage.put("currentItem", page.getTotalElements());
        resultPage.put("totalPages", page.getTotalPages());
    }

    private Student checkPattern(StudentRespondDto studentDto) throws UserValidateException, ParseException {
        if (!studentDto.getStudentCode().matches(RegexContant.STUDENT_CODE_REGEX)) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.code.pattern"));
        }
        if (!studentDto.getGrade().matches(RegexContant.GRADE_CODE_REGEX)) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.class.pattern"));
        }
        if (!studentDto.getDateOfBirth().matches(RegexContant.DATE_OF_BIRTH)) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.dateOfBirth.pattern"));
        }
        if (!studentDto.getCardId().matches(RegexContant.CARD_CODE_REGEX)) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.cardId.pattern"));
        }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse(studentDto.getDateOfBirth());
        Student student = ObjectMapperUtils.toEntity(studentDto, Student.class);
        student.setDateOfBirth(date);
        return student;
    }

    private void checkNullOrEmpty(StudentRespondDto studentDto) throws UserValidateException {
        if (CommonUtils.isNullOrEmpty(studentDto.getStudentCode()) ||
                CommonUtils.isNullOrEmpty(studentDto.getRoomId().toString()) ||
                CommonUtils.isNullOrEmpty(studentDto.getStudentCode()) ||
                CommonUtils.isNullOrEmpty(studentDto.getCardId()) ||
                CommonUtils.isNullOrEmpty(studentDto.getDateOfBirth()) ||
                CommonUtils.isNullOrEmpty(studentDto.getGrade()) ||
                CommonUtils.isNullOrEmpty(studentDto.getAddress())) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.null"));
        }
    }

    private void checkExisted(StudentRespondDto studentDto) throws UserValidateException {
        if (studentRepository.existsByStudentCode(studentDto.getStudentCode())) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.code"));
        }
        if (studentRepository.existsByCardId(studentDto.getCardId())) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.cardId.exists"));
        }
    }
}
