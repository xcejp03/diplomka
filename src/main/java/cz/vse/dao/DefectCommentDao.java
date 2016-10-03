package cz.vse.dao;

import cz.vse.entity.DefectComment;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface DefectCommentDao {

    public void saveDefectComment(DefectComment defectComment);
    public void deleteDefectComment(DefectComment defectComment);
    public void updateDefectComment(DefectComment defectComment);
    public List<DefectComment> getAllDefectComment(DefectComment defectComment);
    public DefectComment getDefectCommentById(long id);
}
