package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.repository.RoleRepository;
import com.web.assgiment.dormitory.repository.UserRepository;
import com.web.assgiment.dormitory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

}
