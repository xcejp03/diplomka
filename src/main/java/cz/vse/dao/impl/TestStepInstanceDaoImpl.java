package cz.vse.dao.impl;

import cz.vse.dao.TestStepInstanceDao;
import cz.vse.entity.TSInstance;
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
public class TestStepInstanceDaoImpl implements TestStepInstanceDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;
    @Override
    public void saveTestStepInstance(TSInstance TSInstance) {
        l.debug("Saving TSInstance: " + TSInstance);
        em.persist(TSInstance);
        l.info("TSInstance saved successfully. TSInstance detail: " + TSInstance);
    }

    @Override
    public void deleteTestStepInstance(TSInstance TSInstance) {
        l.debug("Deleting TSInstance: " + TSInstance);
        em.remove(TSInstance);
        l.info("TSInstance deleted successfully. TSInstance detail: " + TSInstance);
    }

    @Override
    public void updateTestStepInstance(TSInstance TSInstance) {
        l.debug("Updating TSInstance: " + TSInstance);
        em.merge(TSInstance);
        l.info("TSInstance updated successfully. TSInstance detail: " + TSInstance);
    }

    @Override
    public List<TSInstance> getAllTestStepInstances() {
        l.debug("Getting all TSInstance");
        List<TSInstance> resultList = em.createQuery("select d from TSInstance d").getResultList();
        l.info("TestStepInstances gotten successfully. TSInstance detail: " + resultList.toString());
        return null;
    }

    @Override
    public TSInstance getTestStepInstanceById(long id) {
        l.debug("Getting TSInstance by id: " + id);
        TSInstance TSInstance = em.find(TSInstance.class, id);
        l.info("Gotten TSInstance successfully. TSInstance detail: " + TSInstance);
        return null;
    }
}
