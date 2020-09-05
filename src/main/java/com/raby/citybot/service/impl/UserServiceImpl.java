package com.raby.citybot.service.impl;

import com.raby.citybot.repository.exception.CityBotRepositoryException;
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
    public boolean add(UserDto userDto) {
        return userRepository.add(userMapper.toEntity(userDto));
    }

    @Override
    public boolean delete(Long id) {
        return userRepository.delete(id);
    }

    @Override
    public boolean update(UserDto userDto) {
        return userRepository.update(userMapper.toEntity(userDto));
    }

    @Override
    public List<UserDto> query(Specification specification) {
        try {
            return userMapper.toListDto(userRepository.find(specification));
        } catch (CityBotRepositoryException e) {
            throw new CityBotServiceException(e);
        }
    }

    public List<UserDto> findUserByLogin(String login) throws CityBotServiceException {
        try {
            return userMapper.toListDto(userRepository.find(new FindUserByLoginSpecification(login)));
        } catch (CityBotRepositoryException e) {
            throw new CityBotServiceException(e);
        }
    }

    public List<UserDto> findUserByEmail(String email) throws CityBotServiceException {
        try {
            return userMapper.toListDto(userRepository.find(new FindUserByEmailSpecification(email)));
        } catch (CityBotRepositoryException e) {
            throw new CityBotServiceException(e);
        }
    }

    public List<UserDto> findUserByLoginOrEmail(String loginOrEmail) throws CityBotServiceException {
        try {
            return userMapper.toListDto(userRepository.find(new FindUserByLoginOrEmailSpecification(loginOrEmail)));
        } catch (CityBotRepositoryException e) {
            throw new CityBotServiceException(e);
        }
    }

    public List<UserDto> findUserById(long id) throws CityBotServiceException {
        try {
            return userMapper.toListDto(userRepository.find(new FindUserByIdSpecification(id)));
        } catch (CityBotRepositoryException e) {
            throw new CityBotServiceException(e);
        }
    }
}
