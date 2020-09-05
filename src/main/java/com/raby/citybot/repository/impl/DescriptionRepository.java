package com.raby.citybot.repository.impl;

import com.raby.citybot.repository.CommonRepository;
import com.raby.citybot.repository.exception.CityBotRepositoryException;
import com.raby.citybot.repository.model.City;
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
import java.util.Optional;

@Component
@Transactional
public class DescriptionRepository implements CommonRepository<Description> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean add(Description entity) {
        entityManager.persist(entity);
        return entity.equals(entityManager.find(City.class, entity.getId()));
    }

    @Override
    public boolean update(Description entity) {
        Description descriptionToUpdate = entityManager.find(Description.class, entity.getId());
        if (descriptionToUpdate == null) {
            return false;
        }
        descriptionToUpdate.setDescription(entity.getDescription());
        descriptionToUpdate.setCity(entity.getCity());
        return entity.equals(entityManager.find(Description.class, entity.getId()));
    }

    @Override
    public boolean delete(long id) {
        Description descriptionToDelete = entityManager.find(Description.class, id);
        if (descriptionToDelete == null) {
            return false;
        }
        entityManager.remove(entityManager.find(Description.class, id));
        return entityManager.find(Description.class, id) == null;
    }

    @Override
    public List<Description> find(Specification specification) throws CityBotRepositoryException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Description> descriptionCriteriaQuery = criteriaBuilder.createQuery(Description.class);
        Root<Description> descriptionRoot = descriptionCriteriaQuery.from(Description.class);
        descriptionCriteriaQuery.select(descriptionRoot).where(specification.toPredicate(descriptionRoot, descriptionCriteriaQuery, criteriaBuilder));
        Query query = entityManager.createQuery(descriptionCriteriaQuery);
        return Optional.ofNullable(query.getResultList()).orElseThrow(() -> new CityBotRepositoryException("Entity not found"));
    }
}
