package com.raby.citybot.controller;

import com.raby.citybot.repository.impl.DescriptionRepository;
import com.raby.citybot.repository.model.City;
import com.raby.citybot.repository.impl.CityRepository;
import com.raby.citybot.repository.model.Description;
import com.raby.citybot.repository.specification.FindDescriptionByCityName;
import com.raby.citybot.service.dto.CityDto;
import com.raby.citybot.service.dto.DescriptionDto;
import com.raby.citybot.service.dto.mapper.CityDtoMapper;
import com.raby.citybot.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@ComponentScan
public class CityInfoController {

    private CityRepository repository;
    private CityDtoMapper mapper;
    private CityServiceImpl cityService;

    @Autowired
    public CityInfoController(CityRepository repository, CityDtoMapper mapper, CityServiceImpl cityService) {
        this.repository = repository;
        this.mapper = mapper;
        this.cityService = cityService;
    }

    @PostMapping("/city")
    public City addCity(@RequestBody CityDto city) {
        return repository.add(mapper.toEntity(city));
    }

    @PutMapping("/city")
    public CityDto update(@RequestBody CityDto city) {
        return cityService.update(city);
    }

    @DeleteMapping(value = "/city/{id}", produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }
}
