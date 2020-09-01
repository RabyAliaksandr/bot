package com.raby.citybot.repository.specification;

import com.raby.citybot.repository.model.City;
import com.raby.citybot.repository.model.Description;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FindDescriptionByCityName implements Specification<Description> {

    private String name;

    public FindDescriptionByCityName(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Description> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(root.join("city").get("name"), name);
        System.out.println(criteriaBuilder.and(predicate));
        return criteriaBuilder.and(predicate);
    }
}
