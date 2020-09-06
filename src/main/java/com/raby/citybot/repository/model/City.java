package com.raby.citybot.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class City implements AbstractEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "impossible empty name")
    @Size(min = 2, max = 100, message = "name longer than 100 characters is invalid")
    private String name;
    @OneToOne(mappedBy = "city", cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Description description;

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        if (description == null) {
            if (this.description != null) {
                this.description.setCity(null);
            }
        } else {
            description.setCity(this);
        }
        this.description = description;
    }

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

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(name, city.name) &&
                Objects.equals(description, city.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
