package cz.vse.dao.impl;

import cz.vse.dao.TestSuiteDao;
import cz.vse.entity.TestSuite;
import cz.vse.entity.TestSuite;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
@Transactional
public class TestSuiteDaoImpl implements TestSuiteDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;
    @Override
    public void saveTestSuite(TestSuite testSuite) {
        l.debug("Saving testSuite: " + testSuite);
        em.persist(testSuite);
        l.info("TestSuite saved successfully. TestSuite detail: " + testSuite);
    }

    @Override
    public void deleteTestSuite(Long testSuiteId) {
        l.debug("Deleting testSuite: " + testSuiteId);
        TestSuite testSuite = getTestSuiteById(testSuiteId);
        em.remove(testSuite);
        l.info("TestSuite deleted successfully. TestSuite detail: " + testSuiteId);
    }

    @Override
    public void updateTestSuite(TestSuite testSuite) {
        l.debug("Updating testSuite: " + testSuite);
        em.merge(testSuite);
        l.info("TestSuite updated successfully. TestSuite detail: " + testSuite);
    }

    @Override
    public List<TestSuite> getAll(TestSuite testSuite) {
        l.debug("Getting all testSuite");
        List<TestSuite> resultList = em.createQuery("select d from TestSuite d").getResultList();
        l.info("TestSuites gotten successfully. TestSuite detail: " + resultList.toString());
        return null;
    }

    @Override
    public TestSuite getTestSuiteById(long id) {
        l.debug("Getting testSuite by id: " + id);
        TestSuite testSuite = em.find(TestSuite.class, id);
        l.info("Gotten testSuite successfully. TestSuite detail: " + testSuite);
        return null;
    }
}
