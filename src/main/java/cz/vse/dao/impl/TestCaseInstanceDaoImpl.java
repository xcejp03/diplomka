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
    public void saveTestCaseInstance(TCInstance tcInstance) {
        l.debug("Saving TCInstance: " + tcInstance);
        em.persist(tcInstance);
//        l.info(tcInstance.getId());
        l.info("TCInstance saved successfully. TCInstance detail: " + tcInstance);
    }

    @Override
    public void deleteTestCaseInstance(TCInstance tcInstance) {
        l.debug("Deleting TCInstance: " + tcInstance);
        em.remove(tcInstance);
        l.info("TCInstance deleted successfully. TCInstance detail: " + tcInstance);
    }

    @Override
    public void updateTestCaseInstance(TCInstance tcInstance) {
        l.debug("Updating TCInstance: " + tcInstance);
        em.merge(tcInstance);
        l.info("TCInstance updated successfully. TCInstance detail: " + tcInstance);
    }

    @Override
    public List<TCInstance> getAllTestCaseInstances() {
        l.debug("Getting all TCInstance");
        List<TCInstance> resultList = em.createQuery("select t from TCInstance t").getResultList();
        l.info("TCInstance gotten successfully. TCInstance detail: " + resultList.toString());
        return resultList;
    }

    @Override
    public TCInstance getTestCaseInstanceById(long id) {
        l.debug("Getting TCInstance by id: " + id);
        TCInstance tcInstance = em.find(TCInstance.class, id);
        l.info("TCInstance gotten successfully. TCInstance detail: " + tcInstance);
        return tcInstance;
    }
}
