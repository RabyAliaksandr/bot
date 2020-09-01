package com.raby.citybot.service.impl;

import com.raby.citybot.repository.impl.DescriptionRepository;
import com.raby.citybot.repository.specification.FindDescriptionByCityName;
import com.raby.citybot.service.CommonService;
import com.raby.citybot.service.dto.DescriptionDto;
import com.raby.citybot.service.dto.mapper.DescriptionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class DescriptionServiceImpl implements CommonService<DescriptionDto> {

    private DescriptionDtoMapper mapper;
    private DescriptionRepository repository;

    @Autowired
    public DescriptionServiceImpl(DescriptionDtoMapper mapper, DescriptionRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }
    @Override
    public void add(DescriptionDto descriptionDto) {
        repository.add(mapper.toEntity(descriptionDto));
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void update(DescriptionDto descriptionDto) {
        repository.update(mapper.toEntity(descriptionDto));
    }

    @Override
    public List<DescriptionDto> query(Specification specification) {
        return mapper.toListDto(repository.find(specification));
    }

    public List<DescriptionDto> findDescriptionByNewsName(String name) {
        return query(new FindDescriptionByCityName(name));
    }
}
