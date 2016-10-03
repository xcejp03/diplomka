package cz.vse.dao.impl;

import cz.vse.dao.DefectCommentDao;
import cz.vse.entity.DefectComment;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class DefectCommentDaoImpl implements DefectCommentDao {
    private final Logger l = Logger.getLogger(this.getClass());

    @Override
    public void addDefectComment(DefectComment defectComment) {

    }

    @Override
    public void deleteDefectComment(DefectComment defectComment) {

    }

    @Override
    public void updateDefectComment(DefectComment defectComment) {

    }

    @Override
    public List<DefectComment> getAllDefectComment(DefectComment defectComment) {
        return null;
    }

    @Override
    public DefectComment getDefectCommentById(long id) {
        return null;
    }
}
