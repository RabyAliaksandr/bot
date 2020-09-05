package com.raby.citybot.controller;

import com.raby.citybot.service.CommonService;
import com.raby.citybot.service.dto.CityDto;
import com.raby.citybot.service.impl.CityServiceImpl;
import com.raby.citybot.service.validator.CheckingExistInDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@ComponentScan
public class CityInfoController {

    private final CommonService<CityDto> cityService;
    private final CheckingExistInDataBase checkingExist;

    @Autowired
    public CityInfoController(CityServiceImpl cityService, CheckingExistInDataBase checkingExist) {
        this.cityService = cityService;
        this.checkingExist = checkingExist;
    }

    @PostMapping("/city")
    public ResponseEntity<String> addCity(@RequestBody CityDto city) {
        if (checkingExist.checkCity(city.getName())) {
            return new ResponseEntity("This city is already in the database", HttpStatus.BAD_REQUEST);
        }
        if (!cityService.add(city)) {
            return new ResponseEntity("The city has not been added", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("The city added successfully", HttpStatus.OK);
    }

    @PutMapping("/city")
    public ResponseEntity update(@RequestBody CityDto city) {
        if (cityService.update(city)) {
            return new ResponseEntity("The city updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity("The city has not been updated", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/city/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (cityService.delete(id)) {
            return new ResponseEntity("The city deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity("The city has not been deleted", HttpStatus.BAD_REQUEST);
        }
    }
}
