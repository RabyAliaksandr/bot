package com.raby.citybot.controller;

import com.raby.citybot.service.CommonService;
import com.raby.citybot.service.dto.CityDto;
import com.raby.citybot.service.validator.CheckingExistInDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@ComponentScan
public class CityInfoController {

    private static final String CITY_ADDED = "The city added successfully";
    private static final String CITY_EXIST = "This city is already in the database";
    private static final String CITY_NOT_ADDED = "The city has not been added";
    private static final String CITY_UPDATED = "The city updated successfully";
    private static final String CITY_NOT_UPDATED = "The city has not been updated";
    private static final String CITY_DELETED = "The city deleted successfully";
    private static final String CITY_NOT_DELETED = "The city has not been deleted";
    private static final String ID = "id";
    @Resource
    private CommonService<CityDto> cityService;
    private final CheckingExistInDataBase checkingExist;

    @Autowired
    public CityInfoController(CheckingExistInDataBase checkingExist) {
        this.checkingExist = checkingExist;
    }

    @PostMapping("/city")
    public ResponseEntity addCity(@RequestBody CityDto city) {
        if (checkingExist.checkCity(city.getName())) {
            return new ResponseEntity(CITY_EXIST, HttpStatus.BAD_REQUEST);
        }
        if (!cityService.add(city)) {
            return new ResponseEntity(CITY_NOT_ADDED, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(CITY_ADDED, HttpStatus.OK);
    }

    @PutMapping("/city")
    public ResponseEntity update(@RequestBody CityDto city) {
        if (cityService.update(city)) {
            return new ResponseEntity(CITY_UPDATED, HttpStatus.OK);
        } else {
            return new ResponseEntity(CITY_NOT_UPDATED, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/city/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable(ID) Long id) {
        if (cityService.delete(id)) {
            return new ResponseEntity(CITY_DELETED, HttpStatus.OK);
        } else {
            return new ResponseEntity(CITY_NOT_DELETED, HttpStatus.BAD_REQUEST);
        }
    }
}
