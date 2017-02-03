package cz.vse.service.impl;

import cz.vse.dto.DefectCommentDTO;
import cz.vse.dto.DefectDTO;
import cz.vse.entity.Defect;
import cz.vse.entity.DefectComment;
import cz.vse.entity.DefectStatusEnum;
import cz.vse.entity.Person;
import cz.vse.repository.DefectCommentRepository;
import cz.vse.service.DefectCommentService;
import cz.vse.service.DefectService;
import cz.vse.service.PersonService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private PersonService personService;

    public void createComment(Defect defect, Person commentAuthor, String commentText) {
        l.info("creating comment: " + commentText);
        DefectComment defectComment = new DefectComment();
        defectComment.setDefect(defect);
        defectComment.setAuthor(commentAuthor);
        defectComment.setCreatedDateTime(LocalDateTime.now());
        defectComment.setCommentText(commentText);
        defectCommentRepository.save(defectComment);
    }

    public void createComment(DefectCommentDTO defectCommentDTO) {
        l.info("creating comment: ");
        DefectComment defectComment;
        defectComment = mapper.map(defectCommentDTO, DefectComment.class);
        defectComment.setCreatedDateTime(LocalDateTime.now());
        defectCommentRepository.save(defectComment);
    }

    public void updateComment(DefectComment commentToUpdate) {
        l.info("updating comment: " + commentToUpdate);
        defectCommentRepository.save(commentToUpdate);
    }

    public void updateComment(DefectCommentDTO defectCommentDTO) {
        l.info("updating comment: " + defectCommentDTO);
        DefectComment defectComment = findCommentById(defectCommentDTO.getId());
        mapper.map(defectCommentDTO, defectComment);
        defectCommentRepository.save(defectComment);
    }

    public void deleteComment(DefectComment commentToDelete) {
        l.info("deleting comment: " + commentToDelete);
        defectCommentRepository.delete(commentToDelete);
    }

    public void deleteComment(long id) {
        l.info("deleting comment: " + id);
        defectCommentRepository.delete(id);
    }

    public DefectComment findCommentById(long id) {
        l.info("finding comment: " + id);
        DefectComment defectComment;
        defectComment = defectCommentRepository.findOne(id);
        l.info("comment found: "+defectComment);
        return defectComment;
    }

    public DefectCommentDTO findCommentDTOById(long id) {
        l.info("finding comment: " + id);
        DefectComment defectComment = defectCommentRepository.findOne(id);
        DefectCommentDTO defectCommentDTO = mapper.map(defectComment, DefectCommentDTO.class);
        l.info("comment found: "+defectCommentDTO);
        return defectCommentDTO;
    }

    public List<DefectComment> findAllDefectsComments(Defect defect) {
        l.info("finding all defect's comments: " + defect);
        List<DefectComment> defectCommentList;
        defectCommentList = defectCommentRepository.findAllDefectCommentsByDefectOrderById(defect);
        l.info("all defect's comments found:" +defectCommentList);
        return defectCommentList;
    }

    public List<DefectComment> findAllDefectsCommentsByDefectId(Long defectId) {
        l.info("finding all defect's comments - service: " + defectId);
        List<DefectComment> defectCommentList;
        Defect defect = defectService.findDefectById(defectId);
        defectCommentList = defectCommentRepository.findAllDefectCommentsByDefectOrderById(defect);
        l.info("all defect's comments found: "+defectCommentList);
        return defectCommentList;
    }

    public List<DefectCommentDTO> findAllDefectsCommentsDTOAllTest() {
        l.info("finding all defect's comments - service: ");
        List<DefectComment> defectCommentList;
        defectCommentList = defectCommentRepository.findAll();
        List<DefectCommentDTO> defectCommentDTOList;
        defectCommentDTOList = mapper.mapAsList(defectCommentList, DefectCommentDTO.class);
        l.info("found: "+defectCommentDTOList);
        return defectCommentDTOList;
    }

    public List<DefectCommentDTO> findAllDefectCommentDTOByDefect(Defect defect) {
        l.info("with defect: "+defect);
        List<DefectComment> defectCommentList;
        List<DefectCommentDTO> defectCommentDTOList;
        defectCommentList = defectCommentRepository.findAllDefectCommentsByDefectOrderById(defect);
        defectCommentDTOList = mapper.mapAsList(defectCommentList, DefectCommentDTO.class);
        l.info("found: "+defectCommentDTOList);
        return defectCommentDTOList;
    }

    public List<DefectCommentDTO> findAllDefectCommentDTOByDefectId(long id) {
        l.info("with id:"+id);
        List<DefectComment> defectCommentList;
        List<DefectCommentDTO> defectCommentDTOList;
        Defect defect = defectService.findDefectById(id);
        defectCommentList = defectCommentRepository.findAllDefectCommentsByDefectOrderById(defect);
        defectCommentDTOList = mapper.mapAsList(defectCommentList, DefectCommentDTO.class);
        l.info("found: "+defectCommentDTOList);
        return defectCommentDTOList;
    }

    public void writeDefectStatusChange(DefectDTO defectDTO, Person author) {
        l.info("with: "+defectDTO +" and "+author);
        Defect defect = defectService.findDefectById(defectDTO.getId());
        String newStatus = defectDTO.getStatus().name();
        String text = "Status change -> " + newStatus;
        createComment(defect, author, text);
    }

    public void writeDefectAssigneeChange(DefectDTO defectDTO, Person author) {
        l.info("with: "+defectDTO +" and "+author);
        Defect defect = defectService.findDefectById(defectDTO.getId());
        String newAssigneeUsername = personService.findPersonById(defectDTO.getAssignee_id()).getUsername();
        String text = "Assignee change -> " + newAssigneeUsername;
        createComment(defect, author, text);
    }

}
