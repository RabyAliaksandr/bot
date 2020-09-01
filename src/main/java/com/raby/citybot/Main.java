package com.raby.citybot;

import com.raby.citybot.bot.TelegramCityBot;
import com.raby.citybot.repository.impl.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
public class Main {
    @Autowired
private static DescriptionRepository descriptionRepository;
    public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        botsApi.registerBot(new TelegramCityBot(descriptionRepository));
        SpringApplication.run(Main.class, args);
    }
}
