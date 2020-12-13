package com.web.assgiment.dormitory.service;

import com.web.assgiment.dormitory.domain.dto.request.LoginUserDto;
import com.web.assgiment.dormitory.domain.dto.request.RegisterUserDto;
import com.web.assgiment.dormitory.exception.BadRequestException;
import com.web.assgiment.dormitory.exception.UserValidateException;

public interface UserService {
    void registerAccount(RegisterUserDto userDto) throws UserValidateException, BadRequestException;

    String generateToken(LoginUserDto userDto);
}
