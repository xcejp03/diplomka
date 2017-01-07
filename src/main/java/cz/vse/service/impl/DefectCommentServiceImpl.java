package cz.vse.service.impl;

import cz.vse.dto.DefectCommentDTO;
import cz.vse.entity.Defect;
import cz.vse.entity.DefectComment;
import cz.vse.entity.Person;
import cz.vse.repository.DefectCommentRepository;
import cz.vse.service.DefectCommentService;
import cz.vse.service.DefectService;
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
public class DefectCommentServiceImpl implements DefectCommentService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private DefectCommentRepository defectCommentRepository;
    @Autowired
    private MapperFacade mapper;

    @Autowired
    private DefectService defectService;

    public void createComment(Defect defect, Person commentAuthor, String commentText) {
        l.debug("creating comment - service: " + commentText);
        DefectComment defectComment = new DefectComment();
        defectComment.setDefect(defect);
        defectComment.setAuthor(commentAuthor);
        defectComment.setCreatedDateTime(LocalDateTime.now());
        defectComment.setCommentText(commentText);

        defectCommentRepository.save(defectComment);
        l.info("comment created - service");
    }

    public void createComment(DefectCommentDTO defectCommentDTO) {
        l.debug("creating comment - service: ");
        DefectComment defectComment;
        defectComment = mapper.map(defectCommentDTO, DefectComment.class);
        defectComment.setCreatedDateTime(LocalDateTime.now());
        defectCommentRepository.save(defectComment);
        l.info("comment created - service");
    }

    public void updateComment(DefectComment commentToUpdate) {
        l.debug("updating comment - service: " + commentToUpdate);
        defectCommentRepository.save(commentToUpdate);
        l.info("comment updated");
    }

    public void updateComment(DefectCommentDTO defectCommentDTO) {
        l.debug("updating comment - service: " + defectCommentDTO);
        DefectComment defectComment = findCommentById(defectCommentDTO.getId());
        mapper.map(defectCommentDTO, defectComment);
        defectCommentRepository.save(defectComment);
        l.info("comment updated");
    }

    public void deleteComment(DefectComment commentToDelete) {
        l.debug("deleting comment - service: " + commentToDelete);
        defectCommentRepository.delete(commentToDelete);
        l.info("comment deleted");
    }

    public void deleteComment(long id) {
        l.debug("deleting comment - service: " + id);
        defectCommentRepository.delete(id);
        l.info("comment deleted");
    }

    public DefectComment findCommentById(long id) {
        l.debug("finding comment - service: " + id);
        DefectComment defectComment;
        defectComment = defectCommentRepository.findOne(id);
        l.info("comment found");
        return defectComment;
    }

    public DefectCommentDTO findCommentDTOById(long id) {
        l.debug("finding comment - service: " + id);
        DefectComment defectComment = defectCommentRepository.findOne(id);
        DefectCommentDTO defectCommentDTO = mapper.map(defectComment, DefectCommentDTO.class);
        l.info("comment found");
        return defectCommentDTO;
    }

    public List<DefectComment> findAllDefectsComments(Defect defect) {
        l.debug("finding all defect's comments - service: " + defect);
        List<DefectComment> defectCommentList;
        defectCommentList = defectCommentRepository.findAllDefectCommentsByDefectOrderById(defect);
        l.info("all defect's comments found");
        return defectCommentList;
    }

    public List<DefectComment> findAllDefectsCommentsByDefectId(Long defectId) {
        l.debug("finding all defect's comments - service: " + defectId);
        List<DefectComment> defectCommentList;
        Defect defect = defectService.findDefectById(defectId);
        defectCommentList = defectCommentRepository.findAllDefectCommentsByDefectOrderById(defect);
        l.info("all defect's comments found");
        return defectCommentList;
    }

    public List<DefectCommentDTO> findAllDefectsCommentsDTOAllTest() {
        l.debug("finding all defect's comments - service: ");
        List<DefectComment> defectCommentList;
        defectCommentList = defectCommentRepository.findAll();
        List<DefectCommentDTO> defectCommentDTOList;
        defectCommentDTOList = mapper.mapAsList(defectCommentList, DefectCommentDTO.class);
        l.info("all defect's comments found");
        return defectCommentDTOList;
    }

    public List<DefectCommentDTO> findAllDefectCommentDTOByDefect(Defect defect)    {
        List<DefectComment> defectCommentList;
        List<DefectCommentDTO> defectCommentDTOList;
        defectCommentList = defectCommentRepository.findAllDefectCommentsByDefectOrderById(defect);
        defectCommentDTOList = mapper.mapAsList(defectCommentList, DefectCommentDTO.class);
        return defectCommentDTOList;
    }

    public List<DefectCommentDTO> findAllDefectCommentDTOByDefectId(long id)    {
        List<DefectComment> defectCommentList;
        List<DefectCommentDTO> defectCommentDTOList;
        Defect defect = defectService.findDefectById(id);

        defectCommentList = defectCommentRepository.findAllDefectCommentsByDefectOrderById(defect);
        defectCommentDTOList = mapper.mapAsList(defectCommentList, DefectCommentDTO.class);
        return defectCommentDTOList;
    }

}
