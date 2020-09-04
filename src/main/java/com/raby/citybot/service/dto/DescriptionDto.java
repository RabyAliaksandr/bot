package com.raby.citybot.service.dto;

import com.raby.citybot.service.dto.mapper.AbstractDto;

public class DescriptionDto implements AbstractDto {

    private Long id;
    private String description;
    private CityDto city;

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DescriptionDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
