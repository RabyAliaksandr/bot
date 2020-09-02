package com.raby.citybot.service.impl;

import com.raby.citybot.repository.impl.CityRepository;
import com.raby.citybot.repository.model.City;
import com.raby.citybot.service.CommonService;
import com.raby.citybot.service.dto.CityDto;
import com.raby.citybot.service.dto.mapper.CityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CityServiceImpl implements CommonService<CityDto> {

    private CityDtoMapper mapper;
    private CityRepository repository;

    @Autowired
    public CityServiceImpl(CityDtoMapper mapper, CityRepository cityRepository) {
        this.mapper = mapper;
        this.repository = cityRepository;
    }

    @Override
    public CityDto add(CityDto cityDto) {
        return mapper.toDto(repository.add(mapper.toEntity(cityDto)));
    }

    @Override
    public boolean delete(Long id) {
        repository.delete(id);
        return repository.delete(id);
    }

    @Override
    public CityDto update(CityDto cityDto) {
        return mapper.toDto(repository.update(mapper.toEntity(cityDto)));
    }

    @Override
    public List<CityDto> query(Specification specification) {
        return mapper.toListDto(repository.find(specification).get());
    }
}
