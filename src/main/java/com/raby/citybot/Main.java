package com.raby.citybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws TelegramApiRequestException {
        SpringApplication.run(Main.class, args);
    }
}
