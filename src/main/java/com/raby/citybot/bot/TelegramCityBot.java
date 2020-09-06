package com.raby.citybot.bot;

import com.raby.citybot.service.dto.DescriptionDto;
import com.raby.citybot.service.impl.DescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@ComponentScan
public class TelegramCityBot extends TelegramLongPollingBot {

    private static final String message = "There is no information about this city";
    @Value("${bot.token}")
    private String token;
    @Value("${bot.name}")
    private String name;
    private DescriptionServiceImpl service;

    @Autowired
    public TelegramCityBot(DescriptionServiceImpl service) {
        this.service = service;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMessage = update.getMessage();
                SendMessage outMessage = new SendMessage();
                outMessage.setChatId(inMessage.getChatId());
                outMessage.setText(inMessage.getText());
                List<DescriptionDto> descriptionList = service.findDescriptionByNewsName(inMessage.getText().toLowerCase());
                if (descriptionList.isEmpty()) {
                    outMessage.setText(message);
                } else {
                    outMessage.setText(descriptionList.get(0).getDescription());
                }
                execute(outMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}