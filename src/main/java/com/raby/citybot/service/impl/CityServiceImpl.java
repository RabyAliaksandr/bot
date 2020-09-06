package com.raby.citybot.service.impl;

import com.raby.citybot.repository.CommonRepository;
import com.raby.citybot.repository.exception.CityBotRepositoryException;
import com.raby.citybot.repository.model.City;
import com.raby.citybot.repository.specification.FindCityByNameSpecification;
import com.raby.citybot.service.CityService;
import com.raby.citybot.service.dto.CityDto;
import com.raby.citybot.service.dto.mapper.CityDtoMapper;
import com.raby.citybot.service.exception.CityBotServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CityServiceImpl implements CityService {

    private CityDtoMapper mapper;
    private CommonRepository<City> repository;

    @Autowired
    public CityServiceImpl(CityDtoMapper mapper, CommonRepository<City> repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public boolean add(CityDto cityDto) {
        String lowerCaseCityName = cityDto.getName().toLowerCase();
        cityDto.setName(lowerCaseCityName);
        return repository.add(mapper.toEntity(cityDto));
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }

    @Override
    @Transactional
    public boolean update(CityDto cityDto) {
        String lowerCaseCityName = cityDto.getName().toLowerCase();
        cityDto.setName(lowerCaseCityName);
        CityDto cityInDataBase = findCityByName(cityDto.getName());
        if (cityInDataBase != null && cityDto.getName().equals(cityInDataBase.getName()) && cityDto.getId() != cityInDataBase.getId()) {
            throw new CityBotServiceException("This city already exist");
        }
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
