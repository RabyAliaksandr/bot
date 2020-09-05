package com.raby.citybot.controller;

import com.raby.citybot.repository.model.User;
import com.raby.citybot.security.ApiResponse;
import com.raby.citybot.security.JwtAuthenticationResponse;
import com.raby.citybot.security.JwtTokenProvider;
import com.raby.citybot.security.LoginRequest;
import com.raby.citybot.service.dto.UserDto;
import com.raby.citybot.service.dto.mapper.UserDtoMapper;
import com.raby.citybot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@ComponentScan
@RequestMapping("/auth")
public class AuthController {

    private static final String LOGIN_TAKEN = "Login is already taken!";
    private static final String EMAIL_TAKEN = "Email Address already in use!";
    private static final String REGISTERED_SUCCESS = "User registered successfully";
    private static final String USERS_URL = "/user/{username}";
    private final AuthenticationManager authenticationManager;
    private final UserDtoMapper userMapper;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDtoMapper userMapper, UserServiceImpl userService,
                          PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto signUpRequest) {
        if(!userService.findUserByLogin(signUpRequest.getLogin()).isEmpty()) {
            return new ResponseEntity(new ApiResponse(false, LOGIN_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }
        if(!userService.findUserByEmail(signUpRequest.getEmail()).isEmpty()) {
            return new ResponseEntity(new ApiResponse(false, EMAIL_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setSurname(signUpRequest.getSurname());
        user.setPassword(signUpRequest.getPassword());
        user.setEmail(signUpRequest.getEmail());
        user.setLogin(signUpRequest.getLogin());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(signUpRequest.getRole());
        userService.add(userMapper.toDto(user));
            URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path(USERS_URL)
                .buildAndExpand().toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, REGISTERED_SUCCESS));
    }
}