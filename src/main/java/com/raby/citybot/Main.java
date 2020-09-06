package com.raby.citybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.telegram.telegrambots.ApiContextInitializer;

@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        WebMvcAutoConfiguration.class,
        SecurityAutoConfiguration.class
})
@ComponentScan
@EnableWebMvc

public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(Main.class, args);
    }
}
