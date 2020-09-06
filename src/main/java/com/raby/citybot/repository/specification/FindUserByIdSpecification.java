package com.raby.citybot.repository.specification;

import com.raby.citybot.repository.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FindUserByIdSpecification implements Specification<User> {

    private static final String ID = "id";
    private Long userId;

    public FindUserByIdSpecification(Long id) {
        this.userId = id;
    }

    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(root.get(ID), userId);
        return criteriaBuilder.and(predicate);
    }
}
