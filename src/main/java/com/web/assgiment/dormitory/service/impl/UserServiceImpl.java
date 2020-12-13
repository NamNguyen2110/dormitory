package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.common.utils.CommonUtils;
import com.web.assgiment.dormitory.domain.dto.request.LoginUserDto;
import com.web.assgiment.dormitory.domain.dto.request.RegisterUserDto;
import com.web.assgiment.dormitory.domain.entity.ERole;
import com.web.assgiment.dormitory.domain.entity.Role;
import com.web.assgiment.dormitory.domain.entity.User;
import com.web.assgiment.dormitory.exception.BadRequestException;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.repository.RoleRepository;
import com.web.assgiment.dormitory.repository.UserRepository;
import com.web.assgiment.dormitory.service.UserService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import com.web.assgiment.dormitory.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public void registerAccount(RegisterUserDto userDto) throws UserValidateException, BadRequestException {
        if (userRepository.existsByUserName(userDto.getUsername())) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.user.username"));
        }
        if (CommonUtils.isNullOrEmpty(userDto.getUsername()) ||
                CommonUtils.isNullOrEmpty(userDto.getPassword())) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.null"));
        }
        User user = new User(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()));
        Set<String> setRoleName = userDto.getRoles();
        Set<Role> roles = new HashSet<>();
        if (setRoleName == null) {
            Role role = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new BadRequestException(MessageBundle.getMessage("dormitory.message.object.user.role.user")));

            roles.add(role);
        } else {
            setRoleName.forEach(role -> {
                switch (role) {
                    case "admin":
                        try {
                            Role admin = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new BadRequestException(MessageBundle.getMessage("dormitory.message.object.user.role.user")));
                            roles.add(admin);
                        } catch (BadRequestException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "moderator":
                        try {
                            Role admin = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new BadRequestException(MessageBundle.getMessage("dormitory.message.object.user.role.user")));
                            roles.add(admin);
                        } catch (BadRequestException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        try {
                            Role admin = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new BadRequestException(MessageBundle.getMessage("dormitory.message.object.user.role.user")));
                            roles.add(admin);
                        } catch (BadRequestException e) {
                            e.printStackTrace();
                        }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public String generateToken(LoginUserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenUtils.generateToken(authentication);
        return accessToken;
    }
}
