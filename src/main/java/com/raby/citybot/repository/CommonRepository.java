package com.raby.citybot.repository;

import com.raby.citybot.repository.exception.CityBotRepositoryException;
import com.raby.citybot.repository.model.AbstractEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CommonRepository<E extends AbstractEntity> {

    boolean add(E entity);
    boolean update(E entity);
    boolean delete(long id);
    List<E> find(Specification specification) throws CityBotRepositoryException;
}
