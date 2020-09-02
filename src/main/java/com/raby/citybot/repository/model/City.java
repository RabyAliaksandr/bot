package com.raby.citybot.repository.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@Entity
@Access(AccessType.FIELD)
public class City implements AbstractEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "impossible empty name")
    @Size(min = 2, max = 100, message = "name longer than 100 characters is invalid")
    private String name;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "city_info",
    joinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "info_id", referencedColumnName = "id"))
    @JsonIgnore
    private Description description;

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
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
}
