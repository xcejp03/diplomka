package cz.vse.service;

import cz.vse.dao.TestCaseMusterDao;
import cz.vse.entity.TestCaseMuster;
import cz.vse.entity.TestCaseMuster;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TestCaseMusterService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private TestCaseMusterDao testCaseMusterDao;

    public void createTestCaseMuster(TestCaseMuster testCaseMuster) {
        l.debug("creating testCaseMuster - service");
        testCaseMusterDao.saveTestCaseMuster(testCaseMuster);
        l.info("created testCaseMuster - service: " + testCaseMuster);
    }

    public void updateTestCaseMuster(TestCaseMuster testCaseMuster) {
        l.debug("updating testCaseMuster - service");
        testCaseMusterDao.updateTestCaseMuster(testCaseMuster);
        l.info("updated testCaseMuster - service: " + testCaseMuster);
    }

    public void deleteTestCaseMuster(TestCaseMuster testCaseMusterToDelete) {
        l.debug("deleting testCaseMuster - service");
        testCaseMusterDao.deleteTestCaseMuster(testCaseMusterToDelete);
        l.info("testCaseMuster deleted - service: " + testCaseMusterToDelete);
    }

    public void deleteTestCaseMusterById(long testCaseMusterToDeleteById) {
        l.debug("deleting testCaseMuster - service");
        TestCaseMuster testCaseMusterToDelete;
        testCaseMusterToDelete = testCaseMusterDao.getTestCaseMusterById(testCaseMusterToDeleteById);
        testCaseMusterDao.deleteTestCaseMuster(testCaseMusterToDelete);
        l.info("testCaseMuster deleted - service: " + testCaseMusterToDelete);
    }

    public TestCaseMuster findTestCaseMusterById(long id) {
        l.debug("finding testCaseMuster - service");
        TestCaseMuster testCaseMuster;
        testCaseMuster = testCaseMusterDao.getTestCaseMusterById(id);
        l.info("testCaseMuster founf - service: " + id + " - " + testCaseMuster);
        return testCaseMuster;
    }

    public List<TestCaseMuster> findAllTestCaseMusters() {
        l.debug("finding all testCaseMusters - service");
        List<TestCaseMuster> testCaseMusterList;
        testCaseMusterList = testCaseMusterDao.getAllTestCaseMusters();
        l.info("found all testCaseMusters - service: " + testCaseMusterList.toString());
        return testCaseMusterList;
    }
    
}
