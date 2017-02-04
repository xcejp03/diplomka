package cz.vse.service;

import cz.vse.dto.DefectCommentDTO;
import cz.vse.dto.DefectDTO;
import cz.vse.dto.DefectForm;
import cz.vse.entity.Defect;
import cz.vse.entity.DefectComment;
import cz.vse.entity.Person;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface DefectCommentService {
    void createComment(Defect defect, Person commentAuthor, String commentText);

    void createComment(DefectCommentDTO defectCommentDTO);

    void updateComment(DefectComment commentToUpdate);

    void updateComment(DefectCommentDTO defectCommentDTO);

    void deleteComment(DefectComment commentToDelete);

    void deleteComment(long id);

    DefectComment findCommentById(long id);

    DefectCommentDTO findCommentDTOById(long id);

    List<DefectComment> findAllDefectsComments(Defect defect);

    List<DefectComment> findAllDefectsCommentsByDefectId(Long defectId);

    List<DefectCommentDTO> findAllDefectsCommentsDTOAllTest();

    List<DefectCommentDTO> findAllDefectCommentDTOByDefect(Defect defect);

    List<DefectCommentDTO> findAllDefectCommentDTOByDefectId(long id);

    void writeDefectStatusChange(DefectForm defectForm, Person author);

    void writeDefectStatusChange(DefectDTO defectDTO, Person author);

    void writeDefectAssigneeChange(DefectForm defectForm, Person author);

    void writeDefectAssigneeChange(DefectDTO defectDTO, Person author);
}
