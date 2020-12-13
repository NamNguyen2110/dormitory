package com.web.assgiment.dormitory.controller;

import com.web.assgiment.dormitory.common.respond.ResponseData;
import com.web.assgiment.dormitory.domain.dto.request.LoginUserDto;
import com.web.assgiment.dormitory.domain.dto.request.RegisterUserDto;
import com.web.assgiment.dormitory.exception.BadRequestException;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.service.UserService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<ResponseData> registerUser(@RequestBody RegisterUserDto userDto) throws BadRequestException, UserValidateException {
        userService.registerAccount(userDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.create")));
    }

    @GetMapping("/login-user")
    public ResponseEntity<ResponseData> loginUser(@RequestBody LoginUserDto userDto) {
        String accessToken = userService.generateToken(userDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.get"), accessToken));
    }
}
