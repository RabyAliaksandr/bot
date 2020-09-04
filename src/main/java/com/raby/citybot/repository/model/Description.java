package com.raby.citybot.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
//@Access(AccessType.FIELD)
public class Description implements AbstractEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 10, max = 1000, message = "description longer than 1000 characters is invalid")
    private String description;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "city_id")
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", description='" + description + '\'' +
//                ", city=" + city +
                '}';
    }
}
