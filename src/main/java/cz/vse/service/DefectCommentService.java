package cz.vse.service;

import cz.vse.dao.DefectCommentDao;
import cz.vse.dao.DefectDao;
import cz.vse.entity.Defect;
import cz.vse.entity.DefectComment;
import cz.vse.entity.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.PrintConversionEvent;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class DefectCommentService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    DefectCommentDao defectCommentDao;

    @Autowired
    DefectDao defect;

    public void createComment(Defect defect, Person commentAuthor, String commentText) {
        l.debug("creating comment - service: " + commentText);
        DefectComment defectComment = new DefectComment();
        defectComment.setDefect(defect);
        defectComment.setAuthor(commentAuthor);
        defectComment.setCreatedDateTime(LocalDateTime.now());
        defectComment.setCommentText(commentText);

        defectCommentDao.saveDefectComment(defectComment);
        l.info("comment created - service");
    }

    public void deleteComment (DefectComment commentToDelete)   {
        l.debug("deleting comment - service: "+ commentToDelete);
        defectCommentDao.deleteDefectComment(commentToDelete);
        l.info("comment deleted");
    }

    public void updateComment (DefectComment commentToUpdate)   {
        l.debug("updating comment - service: "+commentToUpdate);
        defectCommentDao.updateDefectComment(commentToUpdate);
        l.info("comment updated");
    }

    public DefectComment findCommentById (long id)   {
        l.debug("finding comment - service: "+id);
        DefectComment defectComment;
        defectComment = defectCommentDao.getDefectCommentById(id);
        l.info("comment found");
        return defectComment;
    }

    public List<DefectComment> findAllDefectsComments(Defect defect)  {
        l.debug("finding all defect's comments - service: "+defect);
        List<DefectComment> defectComment;
        defectComment = defectCommentDao.getAllDefectComment(defect);
        l.info("all defect's comments found");
        return defectComment;
    }
}
