package com.raby.citybot.repository.specification;

import com.raby.citybot.repository.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FindUserByLoginOrEmailSpecification implements Specification<User> {

    private String loginOrEmail;


    public FindUserByLoginOrEmailSpecification(String loginOrEmail) {
        this.loginOrEmail = loginOrEmail;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate loginPredicate = criteriaBuilder.equal(root.get("email"), loginOrEmail);
        Predicate emailPredicate = criteriaBuilder.equal(root.get("login"), loginOrEmail);
        predicates.add(loginPredicate);
        predicates.add(emailPredicate);
        return criteriaBuilder.or(predicates.toArray(new Predicate[]{}));
    }
}
