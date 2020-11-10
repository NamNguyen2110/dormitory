package com.web.assgiment.dormitory.service;

import com.web.assgiment.dormitory.dto.PageDto;
import com.web.assgiment.dormitory.dto.StudentDto;
import com.web.assgiment.dormitory.dto.respond.StudentRespondDto;
import com.web.assgiment.dormitory.exception.UserValidateException;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.Map;

public interface StudentService {
    StudentRespondDto saveStudent(StudentDto studentDto) throws UserValidateException, ParseException;

    Map<String, Object> getAllStudent(PageDto pageDto) throws UserValidateException;
}
