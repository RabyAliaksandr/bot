package com.raby.citybot.controller;

import com.raby.citybot.repository.impl.DescriptionRepository;
import com.raby.citybot.repository.model.City;
import com.raby.citybot.repository.impl.CityRepository;
import com.raby.citybot.repository.model.Description;
import com.raby.citybot.repository.specification.FindDescriptionByCityName;
import com.raby.citybot.service.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@ComponentScan
public class CityInfoController {

    private CityRepository repository;
    private DescriptionRepository descriptionRepository;

    @Autowired
    public CityInfoController(CityRepository repository, DescriptionRepository descriptionRepository) {
        this.repository = repository;
        this.descriptionRepository = descriptionRepository;
    }

    @GetMapping("")
    String hello() {
        return "Hello world";
    }
    @GetMapping("/find")
    String find() {
        return "find";
    }

    @PostMapping("")
    public String addCity(@RequestBody City city) {
        repository.add(city);
        return "Added";
    }

    @PutMapping("")
    public void update(@RequestBody City city) {
        repository.update(city);
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }

    @PostMapping("fff")
    public Description findDescription(@RequestBody City city) {
      return  descriptionRepository.find(new FindDescriptionByCityName(city.getName())).get(0);
    }
}
