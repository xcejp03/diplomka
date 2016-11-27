package cz.vse.service;

import cz.vse.dao.DefectCommentDao;
import cz.vse.dao.DefectDao;
import cz.vse.dto.DefectCommentDTO;
import cz.vse.entity.Defect;
import cz.vse.entity.DefectComment;
import cz.vse.entity.Person;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private MapperFacade mapper;

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

    public void createComment(DefectCommentDTO defectCommentDTO) {
        l.debug("creating comment - service: ");
        DefectComment defectComment = new DefectComment();
        defectComment = mapper.map(defectCommentDTO, DefectComment.class);
        defectComment.setCreatedDateTime(LocalDateTime.now());
        defectCommentDao.saveDefectComment(defectComment);
        l.info("comment created - service");
    }

    public void updateComment(DefectComment commentToUpdate) {
        l.debug("updating comment - service: " + commentToUpdate);
        defectCommentDao.updateDefectComment(commentToUpdate);
        l.info("comment updated");
    }

    public void updateComment(DefectCommentDTO defectCommentDTO) {
        l.debug("updating comment - service: " + defectCommentDTO);
        DefectComment defectComment = new DefectComment();
        defectComment = mapper.map(defectCommentDTO, DefectComment.class);
        defectCommentDao.updateDefectComment(defectComment);
        l.info("comment updated");
    }

    public void deleteComment(DefectComment commentToDelete) {
        l.debug("deleting comment - service: " + commentToDelete);
        Long comentId = commentToDelete.getId();
        defectCommentDao.deleteDefectComment(comentId);
        l.info("comment deleted");
    }

    public void deleteComment(long id) {
        l.debug("deleting comment - service: " + id);
        defectCommentDao.deleteDefectComment(id);
        l.info("comment deleted");
    }

    public DefectComment findCommentById(long id) {
        l.debug("finding comment - service: " + id);
        DefectComment defectComment;
        defectComment = defectCommentDao.getDefectCommentById(id);
        l.info("comment found");
        return defectComment;
    }

    public DefectCommentDTO findCommentDTOById(long id) {
        l.debug("finding comment - service: " + id);
        DefectComment defectComment = defectCommentDao.getDefectCommentById(id);
        DefectCommentDTO defectCommentDTO = mapper.map(defectComment, DefectCommentDTO.class);
        l.info("comment found");
        return defectCommentDTO;
    }

    public List<DefectComment> findAllDefectsComments(Defect defect) {
        l.debug("finding all defect's comments - service: " + defect);
        List<DefectComment> defectComment;
        defectComment = defectCommentDao.getAllDefectComment(defect);
        l.info("all defect's comments found");
        return defectComment;
    }

    public List<DefectCommentDTO> findAllDefectsCommentsDTOAllTest() {
        l.debug("finding all defect's comments - service: ");
        List<DefectComment> defectCommentList;
        defectCommentList = defectCommentDao.getAllDefectCommentAllTest();
        List<DefectCommentDTO> defectCommentDTOList;
        defectCommentDTOList = mapper.mapAsList(defectCommentList, DefectCommentDTO.class);
        l.info("all defect's comments found");
        return defectCommentDTOList;
    }
}
