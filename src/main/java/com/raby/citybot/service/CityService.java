package com.raby.citybot.service;

import com.raby.citybot.service.dto.CityDto;

public interface CityService extends CommonService<CityDto> {

    CityDto findCityByName(String name);
}
