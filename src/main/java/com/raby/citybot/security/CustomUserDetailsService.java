package com.raby.citybot.security;

import com.raby.citybot.repository.model.User;
import com.raby.citybot.service.dto.mapper.UserDtoMapper;
import com.raby.citybot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserServiceImpl userService;
    private UserDtoMapper userMapper;

    @Autowired
    public CustomUserDetailsService(UserServiceImpl userService, UserDtoMapper userMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userMapper.toEntity(userService.findUserByLoginOrEmail(usernameOrEmail).get(0));
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userMapper.toEntity(userService.findUserById(id).get(0));
        return UserPrincipal.create(user);
    }
}
