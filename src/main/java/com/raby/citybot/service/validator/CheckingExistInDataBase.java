package com.raby.citybot.service.validator;

import com.raby.citybot.service.impl.CityServiceImpl;
import com.raby.citybot.service.impl.DescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckingExistInDataBase {

    private CityServiceImpl cityService;


    @Autowired
    public CheckingExistInDataBase(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    public boolean checkCity(String name) {
        return cityService.findCityByName(name) != null;
    }
}
