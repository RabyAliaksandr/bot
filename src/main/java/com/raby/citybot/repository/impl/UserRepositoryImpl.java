package com.raby.citybot.repository.impl;

import com.raby.citybot.repository.CommonRepository;
import com.raby.citybot.repository.model.City;
import com.raby.citybot.repository.model.Description;
import com.raby.citybot.repository.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
@Repository
public class UserRepositoryImpl implements CommonRepository<User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User add(User entity) {
        entityManager.persist(entity);
        return entityManager.find(User.class, entity.getId());

    }

    @Override
    public User update(User entity) {
        entityManager.merge(entity);
        return entityManager.find(User.class, entity.getId());
    }

    @Override
    public boolean delete(long id) {
        entityManager.remove(id);
        return entityManager.find(User.class, id) == null;
    }

    @Override
    public Optional<List<User>> find(Specification specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot).where(specification.toPredicate(userRoot, userCriteriaQuery, criteriaBuilder));
        Query query = entityManager.createQuery(userCriteriaQuery);
        return Optional.of(query.getResultList());
    }
}
