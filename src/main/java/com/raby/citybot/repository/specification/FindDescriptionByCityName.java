package com.raby.citybot.repository.specification;

import com.raby.citybot.repository.model.Description;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FindDescriptionByCityName implements Specification<Description> {

    private static final String CITY = "city";
    private static final String NAME = "name";
    private String cityName;

    public FindDescriptionByCityName(String name) {
        this.cityName = name;
    }

    @Override
    public Predicate toPredicate(Root<Description> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(root.join(CITY).get(NAME), cityName);
        return criteriaBuilder.and(predicate);
    }
}
