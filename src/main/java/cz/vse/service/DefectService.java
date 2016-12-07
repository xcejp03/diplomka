package cz.vse.service;

import cz.vse.dto.DefectDTO;
import cz.vse.entity.*;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface DefectService {
    public void createDefect(Defect defect);

    void createDefect(DefectDTO defectDTO);

    void createDefect(String description, PriorityEnum priority, Person assignee,
                      Person reporter, DefectStatusEnum defectStatus, String affectVersion,
                      List<TCInstance> TCInstance, List<TSInstance> TSInstance);

    void updateDefect(DefectDTO defectDTO);

    void deleteDefect(Defect defectToDelete);

    void deleteDefect(long defectToDeleteId);

    Defect findDefectById(long id);

    DefectDTO findDefectDTOById(long id);

    List<Defect> findAllDefects();
}
