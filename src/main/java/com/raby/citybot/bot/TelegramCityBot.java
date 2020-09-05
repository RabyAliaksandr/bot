package com.raby.citybot.bot;

import com.raby.citybot.service.dto.DescriptionDto;
import com.raby.citybot.service.impl.DescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
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

    private DescriptionServiceImpl service;
    @Autowired
    public void setService(DescriptionServiceImpl service) {
        this.service = service;
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
                List<DescriptionDto> descriptionList = service.findDescriptionByNewsName(inMessage.getText().toLowerCase());
                if (descriptionList.isEmpty()) {
                    outMessage.setText("Информация о данном городе отсутсвтует");
                } else {
                    outMessage.setText(descriptionList.get(0).getDescription());
                }
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