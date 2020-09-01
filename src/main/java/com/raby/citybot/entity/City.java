package com.raby.citybot.entity;

public class City extends AbstractEntity {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "City{" +
                "description='" + description + '\'' +
                '}';
    }
}
