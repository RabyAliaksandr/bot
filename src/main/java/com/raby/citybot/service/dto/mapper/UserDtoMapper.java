package com.raby.citybot.service.dto.mapper;

import com.raby.citybot.repository.model.User;
import com.raby.citybot.service.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper extends AbstractMapper<UserDto, User> {


    @Autowired
    public UserDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public User toEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> toListDto(List<User> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<User> toListEntity(List<UserDto> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
