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
public class CityRepositoryImpl implements CommonRepository<City> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean add(City entity) {
        entityManager.persist(entity);
        return entity.equals(entityManager.find(City.class, entity.getId()));
    }

    @Override
    public boolean update(City entity) {
        City city = entityManager.find(City.class, entity.getId());
        if (city == null) {
            return false;
        }
        Description descriptionToUpdate = entityManager.find(Description.class, entity.getDescription().getId());
        descriptionToUpdate.setDescription(entity.getDescription().getDescription());
        entityManager.merge(entity);
        return entity.equals(entityManager.find(City.class, entity.getId()));
    }

    @Override
    public boolean delete(long id) {
        City city = entityManager.find(City.class, id);
        if (city == null) {
            return false;
        }
        entityManager.remove(city);
        return entityManager.find(City.class, id) == null;
    }

    @Override
    public List<City> find(Specification specification) throws CityBotRepositoryException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> cityCriteriaQuery = criteriaBuilder.createQuery(City.class);
        Root<City> cityRoot = cityCriteriaQuery.from(City.class);
        cityCriteriaQuery.select(cityRoot).where(specification.toPredicate(cityRoot, cityCriteriaQuery, criteriaBuilder));
        Query query = entityManager.createQuery(cityCriteriaQuery);
        return Optional.ofNullable(query.getResultList()).orElseThrow(() -> new CityBotRepositoryException("Entity not found"));
    }
}
