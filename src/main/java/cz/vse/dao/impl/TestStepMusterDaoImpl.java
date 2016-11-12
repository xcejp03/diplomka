package cz.vse.dao.impl;

import cz.vse.dao.TestStepMusterDao;
import cz.vse.entity.TSMuster;
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
public class TestStepMusterDaoImpl implements TestStepMusterDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;
    @Override
    public void saveTestStepMuster(TSMuster TSMuster) {
        l.debug("Saving TSMuster: " + TSMuster);
        em.persist(TSMuster);
        l.info("TSMuster saved successfully. TSMuster detail: " + TSMuster);
    }

    @Override
    public void deleteTestStepMuster(TSMuster TSMuster) {
        l.debug("Deleting TSMuster: " + TSMuster);
        em.remove(TSMuster);
        l.info("TSMuster deleted successfully. TSMuster detail: " + TSMuster);
    }

    @Override
    public void updateTestStepMuster(TSMuster TSMuster) {
        l.debug("Updating TSMuster: " + TSMuster);
        em.merge(TSMuster);
        l.info("TSMuster updated successfully. TSMuster detail: " + TSMuster);
    }

    @Override
    public List<TSMuster> getAllTestStepMusters() {
        l.debug("Getting all TSMuster");
        List<TSMuster> resultList = em.createQuery("select d from TSMuster d").getResultList();
        l.info("TestStepMusters gotten successfully. TSMuster detail: " + resultList.toString());
        return null;
    }

    @Override
    public TSMuster getTestStepMusterById(long id) {
        l.debug("Getting TSMuster by id: " + id);
        TSMuster TSMuster = em.find(TSMuster.class, id);
        l.info("Gotten TSMuster successfully. TSMuster detail: " + TSMuster);
        return null;
    }
}
