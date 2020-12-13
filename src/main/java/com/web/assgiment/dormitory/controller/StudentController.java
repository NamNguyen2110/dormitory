package com.web.assgiment.dormitory.controller;

import com.web.assgiment.dormitory.common.respond.ResponseData;
import com.web.assgiment.dormitory.domain.dto.DeleteDto;
import com.web.assgiment.dormitory.domain.dto.PageDto;
import com.web.assgiment.dormitory.domain.dto.StudentDto;
import com.web.assgiment.dormitory.domain.dto.request.StudentRespondDto;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.service.StudentService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "v1/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResponseEntity<ResponseData> getAllStudents(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit) throws UserValidateException {
        PageDto pageDto = new PageDto(offset, limit);
        Map<String, Object> map = studentService.getAllStudent(pageDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.get"), map));
    }

    @PostMapping("/create-student")
    public ResponseEntity<ResponseData> createStudent(@RequestBody StudentRespondDto studentDto) throws UserValidateException, ParseException {
        StudentRespondDto newStudent = studentService.saveStudent(studentDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.create"), studentDto));
    }

    @PutMapping("/delete-student")
    public ResponseEntity<ResponseData> deleteStudent(@RequestBody List<DeleteDto> listId) throws UserValidateException {
        List<StudentRespondDto> dtos = studentService.deleteStudent(listId);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.delete"), dtos));
    }

    @PutMapping("/update-student")
    public ResponseEntity<ResponseData> updateStudent(@RequestBody StudentDto studentDto) throws ParseException, UserValidateException {
        StudentRespondDto dto = studentService.updateStudent(studentDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.update"), dto));
    }

    @GetMapping("/search-room")
    public ResponseEntity<ResponseData> searchStudentByCode(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                            @RequestParam("q") String studentCode) throws UserValidateException {
        PageDto pageDto = new PageDto(offset, limit);
        Map<String, Object> map = studentService.searchByStudentCode(pageDto, studentCode);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.search"),map));
    }
}
