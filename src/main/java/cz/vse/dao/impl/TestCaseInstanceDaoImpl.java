package cz.vse.dao.impl;

import cz.vse.dao.TestCaseInstanceDao;
import cz.vse.entity.Defect;
import cz.vse.entity.TestCaseInstance;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class TestCaseInstanceDaoImpl implements TestCaseInstanceDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;
    @Override
    public void addTestCaseInstance(TestCaseInstance testCaseInstance) {
        l.debug("Saving TestCaseInstance: " + testCaseInstance);
        em.persist(testCaseInstance);
        l.info("TestCaseInstance saved successfully. TestCaseInstance detail: " + testCaseInstance);
    }

    @Override
    public void deleteTestCaseInstance(TestCaseInstance testCaseInstance) {
        l.debug("Deleting TestCaseInstance: " + testCaseInstance);
        em.remove(testCaseInstance);
        l.info("TestCaseInstance deleted successfully. TestCaseInstance detail: " + testCaseInstance);
    }

    @Override
    public void updateTestCaseInstance(TestCaseInstance testCaseInstance) {
        l.debug("Updating TestCaseInstance: " + testCaseInstance);
        em.merge(testCaseInstance);
        l.info("TestCaseInstance updated successfully. TestCaseInstance detail: " + testCaseInstance);
    }

    @Override
    public List<TestCaseInstance> getAllTestCaseInstance() {
        l.debug("Getting all TestCaseInstance");
        List<TestCaseInstance> resultList = em.createQuery("select t from TestCaseInstance t").getResultList();
        l.info("TestCaseInstance gotten successfully. TestCaseInstance detail: " + resultList.toString());
        return null;
    }

    @Override
    public TestCaseInstance getTestCaseInstanceById(long id) {
        l.debug("Getting TestCaseInstance by id: " + id);
        TestCaseInstance testCaseInstance = em.find(TestCaseInstance.class, id);
        l.info("TestCaseInstance gotten successfully. TestCaseInstance detail: " + testCaseInstance);
        return null;
    }
}
