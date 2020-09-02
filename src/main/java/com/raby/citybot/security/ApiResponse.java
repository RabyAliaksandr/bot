package com.raby.citybot.security;

import java.util.Date;
import java.util.List;

public class ApiResponse {
    private Boolean success;
    private String message;
    private List<String> messages;
    private Date timestamp;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(Boolean success, List<String> messages, Date timestamp) {
        this.success = success;
        this.messages = messages;
        this.timestamp = timestamp;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

