package com.raby.citybot.service.dto.mapper;

import com.raby.citybot.repository.model.City;
import com.raby.citybot.service.dto.CityDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityDtoMapper extends AbstractMapper<CityDto, City> {

    @Autowired
    public CityDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public City toEntity(CityDto cityDto) {
        City city = modelMapper.map(cityDto, City.class);
        return city;
    }

    @Override
    public CityDto toDto(City city) {
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public List<CityDto> toListDto(List<City> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<City> toListEntity(List<CityDto> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
