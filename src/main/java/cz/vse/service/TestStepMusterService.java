package cz.vse.service;

import cz.vse.dao.TestStepMusterDao;
import cz.vse.entity.TestStepMuster;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TestStepMusterService {
    private final Logger l = Logger.getLogger(this.getClass());
    
    @Autowired
    TestStepMusterDao testStepMusterDao;

    public void createTestStepMuster(TestStepMuster testStepMuster) {
        l.debug("creating testStepMuster - service");
        testStepMusterDao.saveTestStepMuster(testStepMuster);
        l.info("created testStepMuster - service: " + testStepMuster);
    }

    public void updateTestStepMuster(TestStepMuster testStepMuster) {
        l.debug("updating testStepMuster - service");
        testStepMusterDao.updateTestStepMuster(testStepMuster);
        l.info("updated testStepMuster - service: " + testStepMuster);
    }

    public void deleteTestStepMuster(TestStepMuster testStepMusterToDelete) {
        l.debug("deleting testStepMuster - service");
        testStepMusterDao.deleteTestStepMuster(testStepMusterToDelete);
        l.info("testStepMuster deleted - service: " + testStepMusterToDelete);
    }

    public void deleteTestStepMusterById(long testStepMusterToDeleteById) {
        l.debug("deleting testStepMuster - service");
        TestStepMuster testStepMusterToDelete;
        testStepMusterToDelete = testStepMusterDao.getTestStepMusterById(testStepMusterToDeleteById);
        testStepMusterDao.deleteTestStepMuster(testStepMusterToDelete);
        l.info("testStepMuster deleted - service: " + testStepMusterToDelete);
    }

    public TestStepMuster findTestStepMusterById(long id) {
        l.debug("finding testStepMuster - service");
        TestStepMuster testStepMuster;
        testStepMuster = testStepMusterDao.getTestStepMusterById(id);
        l.info("testStepMuster founf - service: " + id + " - " + testStepMuster);
        return testStepMuster;
    }

    public List<TestStepMuster> findAllTestStepMusters() {
        l.debug("finding all testStepMusters - service");
        List<TestStepMuster> testStepMusterList;
        testStepMusterList = testStepMusterDao.getAllTestStepMusters();
        l.info("found all testStepMusters - service: " + testStepMusterList.toString());
        return testStepMusterList;
    }
    
}
