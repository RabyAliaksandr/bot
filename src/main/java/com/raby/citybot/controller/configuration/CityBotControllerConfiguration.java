package com.raby.citybot.controller.configuration;

import com.raby.citybot.controller.exception.CityBotExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class CityBotControllerConfiguration {

    @Bean
    public CityBotExceptionHandler controllerException() {
        return new CityBotExceptionHandler();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
