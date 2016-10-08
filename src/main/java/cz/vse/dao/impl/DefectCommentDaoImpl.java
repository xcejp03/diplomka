package cz.vse.dao.impl;

import cz.vse.dao.DefectCommentDao;
import cz.vse.entity.DefectComment;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
@ImportResource("classpath:ApplicationContext.xml")
public class DefectCommentDaoImpl implements DefectCommentDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;

    @Override
    public void saveDefectComment(DefectComment defectComment) {
        l.debug("Saving DefectComment: " + defectComment);
        em.persist(defectComment);
        l.info("DefectComment saved successfully. DefectComment detail: " + defectComment);
    }

    @Override
    public void deleteDefectComment(DefectComment defectComment) {
        l.debug("Deleting DefectComment: " + defectComment);
        em.remove(defectComment);
        l.info("DefectComment deleted successfully. DefectComment detail: " + defectComment);
    }

    @Override
    public void updateDefectComment(DefectComment defectComment) {
        l.debug("Updating DefectComment: " + defectComment);
        em.merge(defectComment);
        l.info("DefectComment updated successfully. DefectComment detail: " + defectComment);
    }

    @Override
    public List<DefectComment> getAllDefectComment(DefectComment defectComment) {
        l.debug("Getting all defectComment");
        List<DefectComment> resultList = em.createQuery("select d from DefectComment d").getResultList();
        l.info("DefectComments gotten successfully. DefectComments detail: " + resultList.toString());
        return resultList;
    }

    @Override
    public DefectComment getDefectCommentById(long id) {
        l.debug("Getting DefectComment by id: " + id);
        DefectComment defectComment = em.find(DefectComment.class, id);
        l.info("DefectComment gotten successfully. DefectComment detail: " + defectComment);
        return defectComment;
    }
}
