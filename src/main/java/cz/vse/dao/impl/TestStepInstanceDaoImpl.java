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
    public void saveTestStepInstance(TSInstance tsInstance) {
        l.debug("Saving TSInstance: " + tsInstance);
        em.persist(tsInstance);
        l.info("TSInstance saved successfully. TSInstance detail: " + tsInstance);
    }

    @Override
    public void deleteTestStepInstance(TSInstance tsInstance) {
        l.debug("Deleting TSInstance: " + tsInstance);
        em.remove(tsInstance);
        l.info("TSInstance deleted successfully. TSInstance detail: " + tsInstance);
    }

    @Override
    public void updateTestStepInstance(TSInstance tsInstance) {
        l.debug("Updating TSInstance: " + tsInstance);
        em.merge(tsInstance);
        l.info("TSInstance updated successfully. TSInstance detail: " + tsInstance);
    }

    @Override
    public List<TSInstance> getAllTestStepInstances() {
        l.debug("Getting all TSInstance");
        List<TSInstance> resultList = em.createQuery("select d from TSInstance d").getResultList();
        l.info("TestStepInstances gotten successfully. TSInstance detail: " + resultList.toString());
        return resultList;
    }

    @Override
    public TSInstance getTestStepInstanceById(long id) {
        l.debug("Getting TSInstance by id: " + id);
        TSInstance tsInstance = em.find(TSInstance.class, id);
        l.info("Gotten TSInstance successfully. TSInstance detail: " + tsInstance);
        return tsInstance;
    }

    @Override
    public List<TSInstance> getAllTestStepInstancesByTCInstanceId(long id) {
        List<TSInstance> resultList;
        resultList = em.createQuery("select tsi from TSInstance tsi where tsi.tcInstance.id = :id")
                .setParameter("id", id)
                .getResultList();
        l.info("resultList: " + resultList.toString());
        return resultList;
    }
}
