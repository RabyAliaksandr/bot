package com.raby.citybot.repository;

import com.raby.citybot.repository.model.AbstractEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CommonRepository<E extends AbstractEntity> {

    void add(E entity);
    void update(E entity);
    void delete(long id);
    List<E> find(Specification specification);
}
