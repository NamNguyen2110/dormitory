package com.web.assgiment.dormitory.controller;

import com.web.assgiment.dormitory.common.respond.ResponseData;
import com.web.assgiment.dormitory.dto.PageDto;
import com.web.assgiment.dormitory.dto.StudentDto;
import com.web.assgiment.dormitory.dto.respond.StudentRespondDto;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.service.StudentService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    public ResponseEntity<ResponseData> createStudent(@RequestBody StudentDto studentDto) throws UserValidateException, ParseException {
        StudentRespondDto newStudent = studentService.saveStudent(studentDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.create"), newStudent));
    }
}
