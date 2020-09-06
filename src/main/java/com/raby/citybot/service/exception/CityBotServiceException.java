package com.raby.citybot.service.exception;

public class CityBotServiceException extends RuntimeException {

    public CityBotServiceException(String message) {
        super(message);
    }

    public CityBotServiceException(Throwable cause) {
        super(cause);
    }
}
