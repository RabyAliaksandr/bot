package com.raby.citybot.repository.specification;

import com.raby.citybot.repository.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FindUserByIdSpecification implements Specification<User> {

    private Long id;

    public FindUserByIdSpecification(Long id) {
        this.id = id;
    }

    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        System.out.println("it is finding by id");
        Predicate predicate = criteriaBuilder.equal(root.get("id"), id);
        return criteriaBuilder.and(predicate);
    }
}
