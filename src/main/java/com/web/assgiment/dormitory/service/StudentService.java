package com.web.assgiment.dormitory.service;

import com.web.assgiment.dormitory.dto.PageDto;
import com.web.assgiment.dormitory.dto.StudentDto;
import com.web.assgiment.dormitory.dto.respond.StudentRespondDto;
import com.web.assgiment.dormitory.exception.UserValidateException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface StudentService {
    StudentRespondDto saveStudent(StudentDto studentDto) throws UserValidateException, ParseException;

    Map<String, Object> getAllStudent(PageDto pageDto) throws UserValidateException;

    List<StudentRespondDto> deleteStudent(List<Integer> listId) throws UserValidateException;

    StudentRespondDto updateStudent(StudentDto studentDto) throws UserValidateException, ParseException;

    Map<String, Object> searchByStudentCode(PageDto pageDto,String studentCode) throws UserValidateException;

}
