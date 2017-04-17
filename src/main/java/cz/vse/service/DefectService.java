package cz.vse.service;

import cz.vse.dto.DefectDTO;
import cz.vse.dto.DefectForm;
import cz.vse.dto.DefectList;
import cz.vse.entity.*;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface DefectService {
    public void createDefect(Defect defect);

    void createDefect(DefectForm defectForm);

    void createDefect(String description, PriorityDefectEnum priority, Person assignee,
                      Person reporter, DefectStatusEnum defectStatus, String affectVersion,
                      List<TCInstance> TCInstance, List<TSInstance> TSInstance);

    void updateDefect(DefectForm defectForm);

    void updateDefect(DefectDTO defectDTO);

    void deleteDefect(Defect defectToDelete);

    void deleteDefect(long defectToDeleteId);

    Defect findDefect(long id);

    DefectDTO findDefectDTO(long id);

    DefectForm findDefectForm(long id);

    DefectList findDefectList(long id);

    List<Defect> findAllDefects();

    List<DefectDTO> findAllDefectDTO();

    List<DefectList> findAllDefectLists();

    List<DefectDTO> findAllDefectDTOByReporterAndStatus(Person person, DefectStatusEnum statusEnum);

    List<DefectDTO> findAllDefectDTOByAssigneeAndStatus(Person person, DefectStatusEnum statusEnum);

    List<DefectList> findAllDefectListsByReporterAndStatus(Person person, DefectStatusEnum statusEnum);

    List<DefectList> findAllDefectListsByAssigneeAndStatus(Person person, DefectStatusEnum statusEnum);

    void changeDefectStatus(DefectForm defectForm, Person author);

    void changeDefectStatus(DefectDTO defectDTO, Person author);

    void changeDefectAssignee(DefectDTO defectDTO, Person author);
}
