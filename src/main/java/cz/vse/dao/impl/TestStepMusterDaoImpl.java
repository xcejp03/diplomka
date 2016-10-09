package cz.vse.dao.impl;

import cz.vse.dao.TestStepMusterDao;
import cz.vse.entity.TestStepMuster;
import cz.vse.entity.TestStepMuster;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class TestStepMusterDaoImpl implements TestStepMusterDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;
    @Override
    public void saveTestStepMuster(TestStepMuster testStepMuster) {
        l.debug("Saving testStepMuster: " + testStepMuster);
        em.persist(testStepMuster);
        l.info("TestStepMuster saved successfully. TestStepMuster detail: " + testStepMuster);
    }

    @Override
    public void deleteTestStepMuster(TestStepMuster testStepMuster) {
        l.debug("Deleting testStepMuster: " + testStepMuster);
        em.remove(testStepMuster);
        l.info("TestStepMuster deleted successfully. TestStepMuster detail: " + testStepMuster);
    }

    @Override
    public void updateTestStepMuster(TestStepMuster testStepMuster) {
        l.debug("Updating testStepMuster: " + testStepMuster);
        em.merge(testStepMuster);
        l.info("TestStepMuster updated successfully. TestStepMuster detail: " + testStepMuster);
    }

    @Override
    public List<TestStepMuster> getAllTestStepMusters() {
        l.debug("Getting all testStepMuster");
        List<TestStepMuster> resultList = em.createQuery("select d from TestStepMuster d").getResultList();
        l.info("TestStepMusters gotten successfully. TestStepMuster detail: " + resultList.toString());
        return null;
    }

    @Override
    public TestStepMuster getTestStepMusterById(long id) {
        l.debug("Getting testStepMuster by id: " + id);
        TestStepMuster testStepMuster = em.find(TestStepMuster.class, id);
        l.info("Gotten testStepMuster successfully. TestStepMuster detail: " + testStepMuster);
        return null;
    }
}
