package com.raby.citybot.service.impl;

import com.raby.citybot.repository.exception.CityBotRepositoryException;
import com.raby.citybot.repository.impl.CityRepository;
import com.raby.citybot.repository.impl.DescriptionRepository;
import com.raby.citybot.repository.model.City;
import com.raby.citybot.repository.model.Description;
import com.raby.citybot.repository.specification.FindCityByNameSpecification;
import com.raby.citybot.service.CommonService;
import com.raby.citybot.service.dto.CityDto;
import com.raby.citybot.service.dto.DescriptionDto;
import com.raby.citybot.service.dto.mapper.CityDtoMapper;
import com.raby.citybot.service.dto.mapper.DescriptionDtoMapper;
import com.raby.citybot.service.exception.CityBotServiceException;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CityServiceImpl implements CommonService<CityDto> {

    private CityDtoMapper mapper;
    private CityRepository repository;

    @Autowired
    public CityServiceImpl(CityDtoMapper mapper, CityRepository cityRepository) {
        this.mapper = mapper;
        this.repository = cityRepository;
    }

    @Override
    public boolean add(CityDto cityDto) {
        String lowerCaseCityName = cityDto.getName().toLowerCase();
        cityDto.setName(lowerCaseCityName);
        return repository.add(mapper.toEntity(cityDto));
    }

    @Override
    public boolean delete(Long id) {
        repository.delete(id);
        return repository.delete(id);
    }

    @Override
    @Transactional
    public boolean update(CityDto cityDto) {
        String lowerCaseCityName = cityDto.getName().toLowerCase();
        cityDto.setName(lowerCaseCityName);
        return repository.update(mapper.toEntity(cityDto));
    }

    @Override
    public List<CityDto> query(Specification specification) {
        try {
            return mapper.toListDto(repository.find(specification));
        } catch (CityBotRepositoryException e) {
            throw new CityBotServiceException(e);
        }
    }

    public CityDto findCityByName(String name) {
        return query(new FindCityByNameSpecification(name.toLowerCase())).stream().findFirst().orElse(null);
    }
}
