package cz.vse.dao.impl;

import cz.vse.dao.TestCaseInstanceDao;
import cz.vse.entity.TCInstance;
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
public class TestCaseInstanceDaoImpl implements TestCaseInstanceDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;
    @Override
    public void saveTestCaseInstance(TCInstance TCInstance) {
        l.debug("Saving TCInstance: " + TCInstance);
        em.persist(TCInstance);
        l.info("TCInstance saved successfully. TCInstance detail: " + TCInstance);
    }

    @Override
    public void deleteTestCaseInstance(TCInstance TCInstance) {
        l.debug("Deleting TCInstance: " + TCInstance);
        em.remove(TCInstance);
        l.info("TCInstance deleted successfully. TCInstance detail: " + TCInstance);
    }

    @Override
    public void updateTestCaseInstance(TCInstance TCInstance) {
        l.debug("Updating TCInstance: " + TCInstance);
        em.merge(TCInstance);
        l.info("TCInstance updated successfully. TCInstance detail: " + TCInstance);
    }

    @Override
    public List<TCInstance> getAllTestCaseInstances() {
        l.debug("Getting all TCInstance");
        List<TCInstance> resultList = em.createQuery("select t from TCInstance t").getResultList();
        l.info("TCInstance gotten successfully. TCInstance detail: " + resultList.toString());
        return null;
    }

    @Override
    public TCInstance getTestCaseInstanceById(long id) {
        l.debug("Getting TCInstance by id: " + id);
        TCInstance TCInstance = em.find(TCInstance.class, id);
        l.info("TCInstance gotten successfully. TCInstance detail: " + TCInstance);
        return null;
    }
}
