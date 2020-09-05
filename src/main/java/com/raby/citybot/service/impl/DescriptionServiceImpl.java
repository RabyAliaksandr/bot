package com.raby.citybot.service.impl;

import com.raby.citybot.repository.exception.CityBotRepositoryException;
import com.raby.citybot.repository.impl.DescriptionRepository;
import com.raby.citybot.repository.specification.FindDescriptionByCityName;
import com.raby.citybot.service.CommonService;
import com.raby.citybot.service.dto.DescriptionDto;
import com.raby.citybot.service.dto.mapper.DescriptionDtoMapper;
import com.raby.citybot.service.exception.CityBotServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DescriptionServiceImpl implements CommonService<DescriptionDto> {

    private DescriptionDtoMapper mapper;
    private DescriptionRepository repository;

    @Autowired
    public DescriptionServiceImpl(DescriptionDtoMapper mapper, DescriptionRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }
    @Override
    public boolean add(DescriptionDto descriptionDto) {
        return repository.add(mapper.toEntity(descriptionDto));
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(DescriptionDto descriptionDto) {
        return repository.update(mapper.toEntity(descriptionDto));
    }

    @Override
    public List<DescriptionDto> query(Specification specification) {
        try {
            return mapper.toListDto(repository.find(specification));
        } catch (CityBotRepositoryException e) {
            throw new CityBotServiceException(e);
        }
    }

    public List<DescriptionDto> findDescriptionByNewsName(String name) {
        return query(new FindDescriptionByCityName(name));
    }
}
