package cz.vse.service;

import cz.vse.dao.TestCaseMusterDao;
import cz.vse.entity.TCMuster;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TCMusterService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private TestCaseMusterDao testCaseMusterDao;

    public void createTestCaseMuster(TCMuster TCMuster) {
        l.debug("creating TCMuster - service");
        testCaseMusterDao.saveTestCaseMuster(TCMuster);
        l.info("created TCMuster - service: " + TCMuster);
    }

    public void updateTestCaseMuster(TCMuster TCMuster) {
        l.debug("updating TCMuster - service");
        testCaseMusterDao.updateTestCaseMuster(TCMuster);
        l.info("updated TCMuster - service: " + TCMuster);
    }

    public void deleteTestCaseMuster(TCMuster TCMusterToDelete) {
        l.debug("deleting TCMuster - service");
        testCaseMusterDao.deleteTestCaseMuster(TCMusterToDelete);
        l.info("TCMuster deleted - service: " + TCMusterToDelete);
    }

    public void deleteTestCaseMusterById(long testCaseMusterToDeleteById) {
        l.debug("deleting TCMuster - service");
        TCMuster TCMusterToDelete;
        TCMusterToDelete = testCaseMusterDao.getTestCaseMusterById(testCaseMusterToDeleteById);
        testCaseMusterDao.deleteTestCaseMuster(TCMusterToDelete);
        l.info("TCMuster deleted - service: " + TCMusterToDelete);
    }

    public TCMuster findTestCaseMusterById(long id) {
        l.debug("finding TCMuster - service");
        TCMuster TCMuster;
        TCMuster = testCaseMusterDao.getTestCaseMusterById(id);
        l.info("TCMuster founf - service: " + id + " - " + TCMuster);
        return TCMuster;
    }

    public List<TCMuster> findAllTestCaseMusters() {
        l.debug("finding all testCaseMusters - service");
        List<TCMuster> TCMusterList;
        TCMusterList = testCaseMusterDao.getAllTestCaseMusters();
        l.info("found all testCaseMusters - service: " + TCMusterList.toString());
        return TCMusterList;
    }
    
}
