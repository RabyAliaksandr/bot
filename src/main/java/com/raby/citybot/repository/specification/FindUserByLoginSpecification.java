package com.raby.citybot.repository.specification;

import com.raby.citybot.repository.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FindUserByLoginSpecification implements Specification<User> {

    private static final String LOGIN = "login";
    private String userLogin;

    public FindUserByLoginSpecification(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate userByLogin = criteriaBuilder.equal(root.get(LOGIN), userLogin);
        return criteriaBuilder.and(userByLogin);
    }
}
