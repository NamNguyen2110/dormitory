package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.domain.entity.User;
import com.web.assgiment.dormitory.repository.UserRepository;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(MessageBundle.getMessage("dormitory.message.object.user.exist.username " + username)));
        return UserDetailsImpl.build(user);
    }
}
