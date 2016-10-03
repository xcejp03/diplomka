package cz.vse.dao.impl;

import cz.vse.dao.TestStepInstanceDao;
import cz.vse.entity.TestStepInstance;
import cz.vse.entity.TestStepInstance;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class TestStepInstanceDaoImpl implements TestStepInstanceDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;
    @Override
    public void addTestStepInstance(TestStepInstance testStepInstance) {
        l.debug("Saving testStepInstance: " + testStepInstance);
        em.persist(testStepInstance);
        l.info("TestStepInstance saved successfully. TestStepInstance detail: " + testStepInstance);
    }

    @Override
    public void deleteTestStepInstance(TestStepInstance testStepInstance) {
        l.debug("Deleting testStepInstance: " + testStepInstance);
        em.remove(testStepInstance);
        l.info("TestStepInstance deleted successfully. TestStepInstance detail: " + testStepInstance);
    }

    @Override
    public void updateTestStepInstance(TestStepInstance testStepInstance) {
        l.debug("Updating testStepInstance: " + testStepInstance);
        em.merge(testStepInstance);
        l.info("TestStepInstance updated successfully. TestStepInstance detail: " + testStepInstance);
    }

    @Override
    public List<TestStepInstance> getAllTestStepInstance() {
        l.debug("Getting all testStepInstance");
        List<TestStepInstance> resultList = em.createQuery("select d from TestStepInstance d").getResultList();
        l.info("TestStepInstances gotten successfully. TestStepInstance detail: " + resultList.toString());
        return null;
    }

    @Override
    public TestStepInstance getTestStepInstanceById(long id) {
        l.debug("Getting testStepInstance by id: " + id);
        TestStepInstance testStepInstance = em.find(TestStepInstance.class, id);
        l.info("Gotten testStepInstance successfully. TestStepInstance detail: " + testStepInstance);
        return null;
    }
}
