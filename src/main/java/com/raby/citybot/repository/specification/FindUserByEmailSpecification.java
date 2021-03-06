package com.raby.citybot.repository.specification;

import com.raby.citybot.repository.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FindUserByEmailSpecification implements Specification<User> {

    private static final String EMAIL = "email";
    private String userEmail;

    public FindUserByEmailSpecification(String email) {
        this.userEmail = email;
    }

    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(root.get(EMAIL), userEmail);
        return criteriaBuilder.and(predicate);
    }
}
