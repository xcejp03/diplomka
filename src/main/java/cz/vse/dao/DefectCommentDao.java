package cz.vse.dao;

import cz.vse.entity.Defect;
import cz.vse.entity.DefectComment;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface DefectCommentDao {

    public void saveDefectComment(DefectComment defectComment);
    public void deleteDefectComment(DefectComment defectComment);
    public void updateDefectComment(DefectComment defectComment);
    public List<DefectComment> getAllDefectComment(Defect defect);
    public DefectComment getDefectCommentById(long id);
}
