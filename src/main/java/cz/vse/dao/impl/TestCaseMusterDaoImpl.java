package cz.vse.dao.impl;

import cz.vse.dao.TestCaseMusterDao;
import cz.vse.entity.TCMuster;
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
public class TestCaseMusterDaoImpl implements TestCaseMusterDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveTestCaseMuster(TCMuster TCMuster) {
        l.debug("Saving TCMuster: " + TCMuster);
        em.persist(TCMuster);
        l.info("TCMuster saved successfully. TCMuster detail: " + TCMuster);
    }

    @Override
    public void deleteTestCaseMuster(TCMuster TCMuster) {
        l.debug("Deleting TCMuster: " + TCMuster);
        em.remove(TCMuster);
        l.info("TCMuster deleted successfully. TCMuster detail: " + TCMuster);
    }

    @Override
    public void updateTestCaseMuster(TCMuster TCMuster) {
        l.debug("Updating TCMuster: " + TCMuster);
        em.merge(TCMuster);
        l.info("TCMuster updated successfully. TCMuster detail: " + TCMuster);
    }

    @Override
    public List<TCMuster> getAllTestCaseMusters() {
        l.debug("Getting all TCMuster");
        List<TCMuster> resultList = em.createQuery("select t from TCMuster t").getResultList();
        l.info("TCMuster gotten successfully. TCMuster detail: " + resultList.toString());
        return null;
    }

    @Override
    public TCMuster getTestCaseMusterById(long id) {
        l.debug("Getting TCMuster by id: " + id);
        TCMuster TCMuster = em.find(TCMuster.class, id);
        l.info("TCMuster gotten successfully. TCMuster detail: " + TCMuster);
        return null;
    }
}
