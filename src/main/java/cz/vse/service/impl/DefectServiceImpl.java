package cz.vse.service.impl;

import cz.vse.dao.DefectCommentDao;
import cz.vse.dao.DefectDao;
import cz.vse.dto.DefectDTO;
import cz.vse.entity.*;
import cz.vse.service.DefectService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class DefectServiceImpl implements DefectService {

    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    DefectCommentDao defectCommentDao;

    @Autowired
    DefectDao defectDao;

    @Autowired
    private MapperFacade mapper;

    public void createDefect(Defect defect) {
        l.debug("creating defect - service");
        defectDao.saveDefect(defect);
        l.info("defect created - service: " + defect.toString());
    }

    public void createDefect(DefectDTO defectDTO) {
        l.debug("creating defect - service");
        Defect defect = new Defect();
        defect = mapper.map(defectDTO, Defect.class);
        defectDao.saveDefect(defect);
        l.info("defect created - service: " + defectDTO.toString());
    }

    public void createDefect(String description, PriorityEnum priority, Person assignee,
                             Person reporter, DefectStatusEnum defectStatus, String affectVersion,
                             List<TCInstance> TCInstance, List<TSInstance> TSInstance) {
        l.debug("creating defect - service ");
        Defect defect = new Defect();
        defect.setDescription(description);
        defect.setPriorityEnum(priority);
        defect.setAssignee(assignee);
        defect.setReporter(reporter);
        defect.setDefectStatusEnum(defectStatus);
        defect.setAffectsVersion(affectVersion);
        defect.setTcInstances(TCInstance);
        defect.setTsInstances(TSInstance);

        defectDao.saveDefect(defect);
        l.info("defect created - service: " + defect.toString());
    }

    public void updateDefect(DefectDTO defectDTO) {
        l.debug("updating defect - service");
        Defect defect = new Defect();
        defect = mapper.map(defectDTO, Defect.class);
        defectDao.updateDefect(defect);
        l.info("updating defect - service: " + defectDTO.toString());
    }

    public void deleteDefect(Defect defectToDelete) {
        l.debug("deleting defect - service");
        Long defectId = defectToDelete.getId();
        defectDao.deleteDefect(defectId);
        l.info("defect deleted - service: " + defectToDelete.toString());
    }

    public void deleteDefect(long defectToDeleteId) {
        l.debug("deleting defect - service");
        defectDao.deleteDefect(defectToDeleteId);
        l.info("defect deleted - service: " + defectToDeleteId);
    }

    public Defect findDefectById(long id) {
        l.debug("finding defect by id - service");
        Defect defect = defectDao.getDefectById(id);
        l.info("found defect by id - service: " + id + " - " + defect.toString());
        return defect;
    }

    public DefectDTO findDefectDTOById(long id) {
        l.debug("finding defect by id - service");
        Defect defect = defectDao.getDefectById(id);
        DefectDTO defectDTO = mapper.map(defect, DefectDTO.class);
        l.info("found defect by id - service: " + id + " - " + defectDTO.toString());
        return defectDTO;
    }

    public List<Defect> findAllDefects() {
        l.debug("finding all defects - service");
        List<Defect> defectList = defectDao.getAllDefects();
        l.info("found all defects - service: " + defectList.toString());
        return defectList;
    }

}
