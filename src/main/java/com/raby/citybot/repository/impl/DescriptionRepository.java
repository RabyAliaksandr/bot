package com.raby.citybot.repository.impl;

import com.raby.citybot.repository.CommonRepository;
import com.raby.citybot.repository.model.Description;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class DescriptionRepository implements CommonRepository<Description> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(Description entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Description entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(entityManager.find(Description.class, id));
    }

    @Override
    public List<Description> find(Specification specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Description> cityCriteriaQuery = criteriaBuilder.createQuery(Description.class);
        Root<Description> cityRoot = cityCriteriaQuery.from(Description.class);
        cityCriteriaQuery.select(cityRoot).where(specification.toPredicate(cityRoot, cityCriteriaQuery, criteriaBuilder));
        Query query = entityManager.createQuery(cityCriteriaQuery);
        return query.getResultList();
    }
}
