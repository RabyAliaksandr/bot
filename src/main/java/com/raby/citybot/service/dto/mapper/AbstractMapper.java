package com.raby.citybot.service.dto.mapper;

import com.raby.citybot.repository.model.AbstractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractMapper<D extends AbstractDto, E extends AbstractEntity> {

    public ModelMapper modelMapper;

    @Autowired
    public AbstractMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    protected abstract E toEntity(D d);
    protected abstract D toDto(E e);
    protected abstract List<D> toListDto(List<E> list);
    protected abstract List<E> toListEntity(List<D> list);
}
