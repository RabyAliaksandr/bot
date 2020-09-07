package com.raby.citybot.service.validator;

import com.raby.citybot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
