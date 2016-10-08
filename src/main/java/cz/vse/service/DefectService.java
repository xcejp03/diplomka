package cz.vse.service;

import cz.vse.dao.DefectCommentDao;
import cz.vse.dao.DefectDao;
import cz.vse.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class DefectService {

    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    DefectCommentDao defectCommentDao;

    @Autowired
    DefectDao defectDao;

    public void createDefect(Defect defect) {
        l.debug("creating defect - service");
        defectDao.saveDefect(defect);
        l.info("defect created - service: " + defect.toString());
    }

    public void createDefect(String description, PriorityEnum priority, Person assignee,
                             Person reporter, DefectStatusEnum defectStatus, String affectVersion,
                             List<TestCaseInstance> testCaseInstance, List<TestStepInstance> testStepInstance) {
        l.debug("creating defect - service ");
        Defect defect = new Defect();
        defect.setDescription(description);
        defect.setPriorityEnum(priority);
        defect.setAssignee(assignee);
        defect.setReporter(reporter);
        defect.setDefectStatusEnum(defectStatus);
        defect.setAffectsVersion(affectVersion);
        defect.setTestCaseInstances(testCaseInstance);
        defect.setTestStepInstances(testStepInstance);

        defectDao.saveDefect(defect);
        l.info("defect created - service: " + defect.toString());
    }

    public void deleteDefect(Defect defectToDelete) {
        l.debug("deleting defect - service");
        defectDao.deleteDefect(defectToDelete);
        l.info("defect deleted - service: " + defectToDelete.toString());
    }

    public void deleteDefect(long defectToDeleteId) {
        l.debug("deleting defect - service");
        Defect defectToDelete;
        defectToDelete = defectDao.getDefectById(defectToDeleteId);
        defectDao.deleteDefect(defectToDelete);
        l.info("defect deleted - service: " + defectToDeleteId + " - " + defectToDelete.toString());
    }

    public void updateDefect(Defect defectToUpdate) {
        l.debug("updating defect - service");
        defectDao.updateDefect(defectToUpdate);
        l.info("updating defect - service: " + defectToUpdate.toString());
    }

    public Defect findDefectById(long id) {
        l.debug("finding defect by id - service");
        Defect defect = defectDao.getDefectById(id);
        l.info("found defect by id - service: " + id + " - " + defect.toString());
        return defect;
    }

    public List<Defect> findAllDefects() {
        l.debug("finding all defects - service");
        List<Defect> defectList = defectDao.getAllDefects();
        l.info("found all defects - service: " + defectList.toString());
        return defectList;
    }

}
