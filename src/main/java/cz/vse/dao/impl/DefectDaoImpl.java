package cz.vse.dao.impl;

import cz.vse.dao.DefectDao;
import cz.vse.entity.Defect;
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
@Transactional("transactionManager")
public class DefectDaoImpl implements DefectDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveDefect(Defect defect) {
        l.debug("Saving defect: " + defect);
        em.persist(defect);
        l.info("Defect saved successfully. Defect detail: " + defect);

    }

    @Override
    public void deleteDefect(long id) {
        l.debug("Deleting defect: " + id);
        Defect defect = new Defect();
        defect = getDefectById(id);
        em.remove(defect);
        l.info("Defect deleted successfully. Defect detail: " + defect);
    }

    @Override
    public void updateDefect(Defect defect) {
        l.debug("Updating defect: " + defect);
        em.merge(defect);
        l.info("Defect updated successfully. Defect detail: " + defect);
    }

    @Override
    public List<Defect> getAllDefects() {
        l.debug("Getting all defect");
        List<Defect> resultList = em.createQuery("select d from Defect d").getResultList();
        l.info("Defects gotten successfully. Defect detail: " + resultList.toString());
        return resultList;
    }

    @Override
    public Defect getDefectById(long id) {
        l.debug("Getting defect by id: " + id);
        Defect defect = em.find(Defect.class, id);
        l.info("Gotten defect successfully. Defect detail: " + defect);
        return defect;
    }

}
