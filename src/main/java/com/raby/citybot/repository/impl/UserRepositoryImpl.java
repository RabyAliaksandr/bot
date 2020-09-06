package com.raby.citybot.repository.impl;

import com.raby.citybot.repository.CommonRepository;
import com.raby.citybot.repository.exception.CityBotRepositoryException;
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
    public boolean add(User entity) {
        entityManager.persist(entity);
        return entity.equals(entityManager.find(User.class, entity.getId()));

    }

    @Override
    public boolean update(User entity) {
        User userToUpdate = entityManager.find(User.class, entity.getId());
        if (userToUpdate == null) {
            return false;
        }
        userToUpdate.setRole(entity.getRole());
        userToUpdate.setEmail(entity.getEmail());
        userToUpdate.setLogin(entity.getLogin());
        userToUpdate.setName(entity.getName());
        userToUpdate.setSurname(entity.getSurname());
        userToUpdate.setPassword(entity.getPassword());
        return entity.equals(entityManager.find(User.class, entity.getId()));
    }

    @Override
    public boolean delete(long id) {
        entityManager.remove(id);
        return entityManager.find(User.class, id) == null;
    }

    @Override
    public List<User> find(Specification specification) throws CityBotRepositoryException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot).where(specification.toPredicate(userRoot, userCriteriaQuery, criteriaBuilder));
        Query query = entityManager.createQuery(userCriteriaQuery);
        return Optional.ofNullable(query.getResultList()).orElseThrow(() -> new CityBotRepositoryException("Entity not found"));
    }
}
