package com.raby.citybot.repository.impl;

import com.raby.citybot.repository.CommonRepository;
import com.raby.citybot.repository.model.Description;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
public class DescriptionRepository implements CommonRepository<Description> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Description add(Description entity) {
        entityManager.persist(entity);
        return entityManager.find(Description.class, entity.getId());
    }

    @Override
    public Description update(Description entity) {
        entityManager.merge(entity);
        return entityManager.find(Description.class, entity.getId());
    }

    @Override
    public boolean delete(long id) {
        entityManager.remove(entityManager.find(Description.class, id));
        return entityManager.find(Description.class, id) == null;
    }

    @Override
    public Optional<List<Description>> find(Specification specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Description> descriptionCriteriaQuery = criteriaBuilder.createQuery(Description.class);
        Root<Description> descriptionRoot = descriptionCriteriaQuery.from(Description.class);
        descriptionCriteriaQuery.select(descriptionRoot).where(specification.toPredicate(descriptionRoot, descriptionCriteriaQuery, criteriaBuilder));
        Query query = entityManager.createQuery(descriptionCriteriaQuery);
        return Optional.ofNullable(query.getResultList());
    }
}
