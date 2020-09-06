package com.raby.citybot.service.dto;

import com.raby.citybot.service.dto.mapper.AbstractDto;


public class CityDto implements AbstractDto {

    private Long id;
    private String name;
    private DescriptionDto description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DescriptionDto getDescription() {
        return description;
    }

    public void setDescription(DescriptionDto description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CityDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description=" + description +
                '}';
    }
}
