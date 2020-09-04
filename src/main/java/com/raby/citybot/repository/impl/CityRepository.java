package com.raby.citybot.repository.impl;

import com.raby.citybot.repository.CommonRepository;
import com.raby.citybot.repository.model.City;
import com.raby.citybot.repository.model.Description;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CityRepository implements CommonRepository<City> {

    @PersistenceContext
    private EntityManager entityManager;
    private DescriptionRepository descriptionRepository;
    @Autowired
    public void setDescriptionRepository(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }


    @Override
    public City add(City entity) {
        entityManager.persist(entity);
        return entityManager.find(City.class, entity.getId());
    }

    @Override
    public City update(City entity) {
        Description description = new Description();
        description.setDescription(entity.getDescription().getDescription());
        description.setId(entity.getId());
        descriptionRepository.update(description);
        City city = entityManager.find(City.class, entity.getId());
        city.setName(entity.getName());
        return entityManager.find(City.class, entity.getId());
    }

    @Override
    public boolean delete(long id) {
        entityManager.remove(entityManager.find(City.class, id));
        return entityManager.find(City.class, id) == null;
    }

    @Override
    public Optional<List<City>> find(Specification specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> cityCriteriaQuery = criteriaBuilder.createQuery(City.class);
        Root<City> cityRoot = cityCriteriaQuery.from(City.class);
        cityCriteriaQuery.select(cityRoot).where(specification.toPredicate(cityRoot, cityCriteriaQuery, criteriaBuilder));
        Query query = entityManager.createQuery(cityCriteriaQuery);
        return Optional.of(query.getResultList());
    }
}
