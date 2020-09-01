package com.raby.citybot.service.dto.mapper;

import com.raby.citybot.repository.model.Description;
import com.raby.citybot.service.dto.DescriptionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DescriptionDtoMapper extends AbstractMapper<DescriptionDto, Description> {

    @Autowired
    public DescriptionDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public Description toEntity(DescriptionDto descriptionDto) {
        return modelMapper.map(descriptionDto, Description.class);
    }

    @Override
    public DescriptionDto toDto(Description description) {
        return modelMapper.map(description, DescriptionDto.class);
    }

    @Override
    public List<DescriptionDto> toListDto(List<Description> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Description> toListEntity(List<DescriptionDto> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
