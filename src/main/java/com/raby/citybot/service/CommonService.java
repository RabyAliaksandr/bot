package com.raby.citybot.service;

import com.raby.citybot.service.dto.mapper.AbstractDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CommonService<E extends AbstractDto> {

    boolean add(E e);

    boolean delete(Long id);

    boolean update(E e);

    List<E> query(Specification<E> specification);
}
