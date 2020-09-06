package com.raby.citybot.controller;

import com.raby.citybot.service.dto.CityDto;
import com.raby.citybot.service.impl.CityServiceImpl;
import com.raby.citybot.service.validator.CheckingExistInDataBase;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class CityInfoControllerTest {

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();
    @InjectMocks
    CityInfoController controller;
    @Mock
    CityServiceImpl cityService;
    @Mock
    CheckingExistInDataBase checkingExistInDataBase;

    @Test
    public void testAdd() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(cityService.add(any(CityDto.class))).thenReturn(true);
        when(checkingExistInDataBase.checkCity(any(String.class))).thenReturn(true);
        CityDto city = new CityDto();
        city.setId(1L);
        city.setName("Name");
        ResponseEntity responseEntity = controller.addCity(city);
        int actual = responseEntity.getStatusCodeValue();
        int expected = 200;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddNegative() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(cityService.add(any(CityDto.class))).thenReturn(false);
        when(checkingExistInDataBase.checkCity(any(String.class))).thenReturn(true);
        CityDto city = new CityDto();
        city.setId(1L);
        city.setName("Name");
        ResponseEntity responseEntity = controller.addCity(city);
        int actual = responseEntity.getStatusCodeValue();
        int expected = 400;
        Assert.assertEquals(expected, actual);
    }
}
