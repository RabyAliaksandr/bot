package com.raby.citybot.bot;

import com.raby.citybot.repository.impl.DescriptionRepository;
import com.raby.citybot.repository.model.Description;
import com.raby.citybot.repository.specification.FindDescriptionByCityName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;

public class TelegramCityBot extends TelegramLongPollingBot {

    private DescriptionRepository repository;

    @Autowired
    public TelegramCityBot(DescriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            //проверяем есть ли сообщение и текстовое ли оно
            if (update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем объект входящего сообщения
                Message inMessage = update.getMessage();
                //Создаем исходящее сообщение
                SendMessage outMessage = new SendMessage();
                //Указываем в какой чат будем отправлять сообщение
                //(в тот же чат, откуда пришло входящее сообщение)
                outMessage.setChatId(inMessage.getChatId());
                //Указываем текст сообщения
                outMessage.setText(inMessage.getText());
                Description description = repository.find(new FindDescriptionByCityName(inMessage.getText())).get(0);
                outMessage.setText(description.getDescription());
//                Отправляем сообщение
                execute(outMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    public String getBotUsername() {
        return "City_InfoBot";
    }

    @Override
    public String getBotToken() {
        return "1277942386:AAHMyDv_vcjfDBjWidIMkpx4QGYVpSDTE-0";
    }
}