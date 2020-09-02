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
    public DescriptionDto add(DescriptionDto descriptionDto) {
        return mapper.toDto(repository.add(mapper.toEntity(descriptionDto)));
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }

    @Override
    public DescriptionDto update(DescriptionDto descriptionDto) {
        return mapper.toDto(repository.update(mapper.toEntity(descriptionDto)));
    }

    @Override
    public List<DescriptionDto> query(Specification specification) {
        System.out.println("now will be thre");
        return mapper.toListDto(repository.find(specification).orElseThrow(ArithmeticException::new));
    }

    public List<DescriptionDto> findDescriptionByNewsName(String name) {
        return query(new FindDescriptionByCityName(name));
    }
}
