package com.raby.citybot.service;

import com.raby.citybot.repository.exception.CityBotRepositoryException;
import com.raby.citybot.repository.impl.CityRepositoryImpl;
import com.raby.citybot.repository.model.City;
import com.raby.citybot.repository.model.Description;
import com.raby.citybot.repository.specification.FindCityByNameSpecification;
import com.raby.citybot.service.dto.CityDto;
import com.raby.citybot.service.dto.DescriptionDto;
import com.raby.citybot.service.dto.mapper.CityDtoMapper;
import com.raby.citybot.service.exception.CityBotServiceException;
import com.raby.citybot.service.impl.CityServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class CityServiceTest {

    private static CityServiceImpl cityService;
    private static CityRepositoryImpl cityRepository;
    private static CityDto cityDto;
    private static DescriptionDto descriptionDto;
    private static City city;
    private static Description description;

    @BeforeClass
    public static void init() {
        CityDtoMapper cityDtoMapper = Mockito.mock(CityDtoMapper.class);
        cityRepository = Mockito.mock(CityRepositoryImpl.class);
        cityService = new CityServiceImpl(cityDtoMapper, cityRepository);
        cityDto = new CityDto();
        descriptionDto = new DescriptionDto();
        cityDto.setId(1L);
        cityDto.setName("minsk");
        descriptionDto.setId(1L);
        descriptionDto.setDescription("Okrestino");
        descriptionDto.setCity(cityDto);
        cityDto.setDescription(descriptionDto);
        city = new City();
        description = new Description();
        city.setId(2L);
        city.setName("minsk");
        description.setId(1L);
        description.setDescription("Okrestino");
        description.setCity(city);
        city.setDescription(description);
        when(cityDtoMapper.toDto(city)).thenReturn(cityDto);
        when(cityDtoMapper.toEntity(cityDto)).thenReturn(city);
        when(cityDtoMapper.toListDto(List.of(city))).thenReturn(List.of(cityDto));
        when(cityDtoMapper.toListEntity(List.of(cityDto))).thenReturn(List.of(city));
    }

    @Test(expected = CityBotServiceException.class)
    public void testUpdate() throws CityBotRepositoryException {
        when(cityRepository.find(any(FindCityByNameSpecification.class))).thenReturn(List.of(city));
        CityDto cityDto1 = new CityDto();
        DescriptionDto descriptionDto1 = new DescriptionDto();
        cityDto1.setId(2L);
        cityDto1.setName("minsk");
        descriptionDto1.setId(1L);
        descriptionDto1.setDescription("Okrestino");
        descriptionDto1.setCity(cityDto1);
        cityDto1.setDescription(descriptionDto1);
        cityService.update(cityDto1);
    }
}
