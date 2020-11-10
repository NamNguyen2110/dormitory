package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.common.utils.CommonUtils;
import com.web.assgiment.dormitory.domain.Room;
import com.web.assgiment.dormitory.domain.Student;
import com.web.assgiment.dormitory.dto.PageDto;
import com.web.assgiment.dormitory.dto.RoomDto;
import com.web.assgiment.dormitory.dto.StudentDto;
import com.web.assgiment.dormitory.dto.respond.StudentRespondDto;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.mapper.ObjectMapperUtils;
import com.web.assgiment.dormitory.repository.RoomRepository;
import com.web.assgiment.dormitory.repository.StudentRepository;
import com.web.assgiment.dormitory.service.StudentService;
import com.web.assgiment.dormitory.utils.MessageBundle;
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
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public StudentRespondDto saveStudent(StudentDto studentDto) throws UserValidateException, ParseException {
        Student student = validateStudentData(studentDto);
        Optional<Room> optional = roomRepository.findById(studentDto.getRoomId());
        if (optional.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        student.setRoom(optional.get());
        student.setStatus(1);
        studentRepository.save(student);
        StudentRespondDto newStudent = ObjectMapperUtils.toDto(student, StudentRespondDto.class);
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

    private void customizePagination(Page<Student> page) {
        List<Student> roomList = page.getContent();
        List<StudentRespondDto> dtos = ObjectMapperUtils.toDto(roomList, StudentRespondDto.class);
        resultPage.put("data", dtos);
        resultPage.put("totalPage", page.getSize());
        resultPage.put("currentPage", page.getNumber());
        resultPage.put("totalItems", page.getTotalElements());
        resultPage.put("totalPages", page.getTotalPages());
    }

    public Student validateStudentData(StudentDto studentDto) throws UserValidateException, ParseException {
        if (CommonUtils.isNullOrEmpty(studentDto.getStudentCode()) ||
                CommonUtils.isNullOrEmpty(studentDto.getRoomId().toString()) ||
                CommonUtils.isNullOrEmpty(studentDto.getStudentCode()) ||
                CommonUtils.isNullOrEmpty(studentDto.getCardId()) ||
                CommonUtils.isNullOrEmpty(studentDto.getDateOfBirth()) ||
                CommonUtils.isNullOrEmpty(studentDto.getGrade()) ||
                CommonUtils.isNullOrEmpty(studentDto.getAddress())) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.null"));
        }
        if (studentRepository.existsByCardId(studentDto.getCardId())) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.cardId"));
        }
        if (studentRepository.existsByStudentCode(studentDto.getStudentCode())) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.code"));
        }
        if (studentDto.getStudentCode().matches("^[B]")) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.code.validate"));
        }
        if (studentDto.getGrade().matches("^[D]")) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.class.validate"));
        }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse(studentDto.getDateOfBirth());
        Student student = ObjectMapperUtils.toEntity(studentDto, Student.class);
        student.setDateOfBirth(date);
        return student;
    }
}
