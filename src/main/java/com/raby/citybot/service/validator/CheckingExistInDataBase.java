package com.raby.citybot.service.validator;

import com.raby.citybot.service.CityService;
import com.raby.citybot.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Component
public class CheckingExistInDataBase {

    private CityService cityService;

    @Autowired
    public CheckingExistInDataBase(CityService cityService) {
        this.cityService = cityService;
    }

    public boolean checkCity(String name) {
        return cityService.findCityByName(name) != null;
    }
}
