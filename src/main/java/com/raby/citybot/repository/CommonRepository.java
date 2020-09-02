package com.raby.citybot.repository;

import com.raby.citybot.repository.model.AbstractEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface CommonRepository<E extends AbstractEntity> {

    E add(E entity);
    E update(E entity);
    boolean delete(long id);
    Optional<List<E>> find(Specification specification);
}
