package com.raby.citybot.service.impl;

import com.raby.citybot.repository.impl.UserRepositoryImpl;
import com.raby.citybot.repository.specification.FindUserByEmailSpecification;
import com.raby.citybot.repository.specification.FindUserByIdSpecification;
import com.raby.citybot.repository.specification.FindUserByLoginOrEmailSpecification;
import com.raby.citybot.repository.specification.FindUserByLoginSpecification;
import com.raby.citybot.service.CommonService;
import com.raby.citybot.service.dto.UserDto;
import com.raby.citybot.service.dto.mapper.UserDtoMapper;
import com.raby.citybot.service.exception.CityBotServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class UserServiceImpl implements CommonService<UserDto> {

    private UserRepositoryImpl userRepository;
    private UserDtoMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepository, UserDtoMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto add(UserDto userDto) {
        return userMapper.toDto(userRepository.add(userMapper.toEntity(userDto)));
    }

    @Override
    public boolean delete(Long id) {
        return userRepository.delete(id);
    }

    @Override
    public UserDto update(UserDto userDto) {
        return userMapper.toDto(userRepository.update(userMapper.toEntity(userDto)));
    }

    @Override
    public List<UserDto> query(Specification specification) {
        return userMapper.toListDto(userRepository.find(specification).orElseThrow());
    }

    public List<UserDto> findUserByLogin(String login) throws CityBotServiceException {
        return userMapper.toListDto(userRepository.find(new FindUserByLoginSpecification(login))
                .orElse(new ArrayList<>()));
    }

    public List<UserDto> findUserByEmail(String email) throws CityBotServiceException {
        return userMapper.toListDto(userRepository.find(new FindUserByEmailSpecification(email))
                .orElse(new ArrayList<>()));
    }

    public List<UserDto> findUserByLoginOrEmail(String loginOrEmail) throws CityBotServiceException {
        return userMapper.toListDto(userRepository.find(new FindUserByLoginOrEmailSpecification(loginOrEmail))
                .orElseThrow(() -> new CityBotServiceException("this user does not exist")));
    }

    public List<UserDto> findUserById(long id) throws CityBotServiceException {
        return userMapper.toListDto(userRepository.find(new FindUserByIdSpecification(id))
                .orElseThrow(() -> new CityBotServiceException("this user does not exist")));
    }


}
