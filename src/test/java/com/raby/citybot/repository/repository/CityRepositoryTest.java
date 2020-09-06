package com.raby.citybot.repository.repository;

import com.raby.citybot.repository.CommonRepository;
import com.raby.citybot.repository.configuration.RepositoryConfig;
import com.raby.citybot.repository.model.City;
import com.raby.citybot.repository.model.Description;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {RepositoryConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class CityRepositoryTest {


    @Autowired
    private CommonRepository<City> repository;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testAdd() {
        City expected = getTestEntity();
        repository.add(expected);
        City actual = entityManager.find(City.class, expected.getId());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDelete() {
        City toDelete = getTestEntity();
        entityManager.persist(toDelete);
        repository.delete(toDelete.getId());
        Assert.assertNull(entityManager.find(City.class, toDelete.getId()));
    }

    @Test
    public void testUpdate() {
        City expected = getTestEntity();
        entityManager.persist(expected);
        expected.setName("Harodnja");
        repository.update(expected);
        City actual = entityManager.find(City.class, expected.getId());
        Assert.assertEquals(expected, actual);
    }

    private City getTestEntity() {
        City expected = new City();
        expected.setName("Minsk");
        Description description = new Description();
        description.setDescription("Volny gorad");
        expected.setDescription(description);
        return expected;
    }

}
