package com.raby.citybot.repository.specification;

import com.raby.citybot.repository.model.City;
import com.raby.citybot.repository.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FindCityByNameSpecification implements Specification<City> {

    private static final String NAME = "name";
    private String name;

    public FindCityByNameSpecification(String name) {
        this.name = name;
    }


    public Predicate toPredicate(Root<City> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(root.get(NAME), name);
        return criteriaBuilder.and(predicate);
    }
}
