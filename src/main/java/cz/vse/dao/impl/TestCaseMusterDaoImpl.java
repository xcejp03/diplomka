package cz.vse.dao.impl;

import cz.vse.dao.TestCaseMusterDao;
import cz.vse.entity.Defect;
import cz.vse.entity.TestCaseMuster;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class TestCaseMusterDaoImpl implements TestCaseMusterDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveTestCaseMuster(TestCaseMuster testCaseMuster) {
        l.debug("Saving TestCaseMuster: " + testCaseMuster);
        em.persist(testCaseMuster);
        l.info("TestCaseMuster saved successfully. TestCaseMuster detail: " + testCaseMuster);
    }

    @Override
    public void deleteTestCaseMuster(TestCaseMuster testCaseMuster) {
        l.debug("Deleting TestCaseMuster: " + testCaseMuster);
        em.remove(testCaseMuster);
        l.info("TestCaseMuster deleted successfully. TestCaseMuster detail: " + testCaseMuster);
    }

    @Override
    public void updateTestCaseMuster(TestCaseMuster testCaseMuster) {
        l.debug("Updating TestCaseMuster: " + testCaseMuster);
        em.merge(testCaseMuster);
        l.info("TestCaseMuster updated successfully. TestCaseMuster detail: " + testCaseMuster);
    }

    @Override
    public List<TestCaseMuster> getAllTestCaseMusters() {
        l.debug("Getting all TestCaseMuster");
        List<TestCaseMuster> resultList = em.createQuery("select t from TestCaseMuster t").getResultList();
        l.info("TestCaseMuster gotten successfully. TestCaseMuster detail: " + resultList.toString());
        return null;
    }

    @Override
    public TestCaseMuster getTestCaseMusterById(long id) {
        l.debug("Getting TestCaseMuster by id: " + id);
        TestCaseMuster testCaseMuster = em.find(TestCaseMuster.class, id);
        l.info("TestCaseMuster gotten successfully. TestCaseMuster detail: " + testCaseMuster);
        return null;
    }
}
