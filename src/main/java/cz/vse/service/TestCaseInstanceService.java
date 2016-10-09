package cz.vse.service;

import cz.vse.dao.TestCaseInstanceDao;
import cz.vse.entity.TestCaseInstance;
import cz.vse.entity.TestCaseInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TestCaseInstanceService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private TestCaseInstanceDao testCaseInstanceDao;

    public void createTestCaseInstance(TestCaseInstance testCaseInstance) {
        l.debug("creating testCaseInstance - service");
        testCaseInstanceDao.saveTestCaseInstance(testCaseInstance);
        l.info("created testCaseInstance - service: " + testCaseInstance);
    }

    public void updateTestCaseInstance(TestCaseInstance testCaseInstance) {
        l.debug("updating testCaseInstance - service");
        testCaseInstanceDao.updateTestCaseInstance(testCaseInstance);
        l.info("updated testCaseInstance - service: " + testCaseInstance);
    }

    public void deleteTestCaseInstance(TestCaseInstance testCaseInstanceToDelete) {
        l.debug("deleting testCaseInstance - service");
        testCaseInstanceDao.deleteTestCaseInstance(testCaseInstanceToDelete);
        l.info("testCaseInstance deleted - service: " + testCaseInstanceToDelete);
    }

    public void deleteTestCaseInstanceById(long testCaseInstanceToDeleteById) {
        l.debug("deleting testCaseInstance - service");
        TestCaseInstance testCaseInstanceToDelete;
        testCaseInstanceToDelete = testCaseInstanceDao.getTestCaseInstanceById(testCaseInstanceToDeleteById);
        testCaseInstanceDao.deleteTestCaseInstance(testCaseInstanceToDelete);
        l.info("testCaseInstance deleted - service: " + testCaseInstanceToDelete);
    }

    public TestCaseInstance findTestCaseInstanceById(long id) {
        l.debug("finding testCaseInstance - service");
        TestCaseInstance testCaseInstance;
        testCaseInstance = testCaseInstanceDao.getTestCaseInstanceById(id);
        l.info("testCaseInstance founf - service: " + id + " - " + testCaseInstance);
        return testCaseInstance;
    }

    public List<TestCaseInstance> findAllTestCaseInstances() {
        l.debug("finding all testCaseInstances - service");
        List<TestCaseInstance> testCaseInstanceList;
        testCaseInstanceList = testCaseInstanceDao.getAllTestCaseInstances();
        l.info("found all testCaseInstances - service: " + testCaseInstanceList.toString());
        return testCaseInstanceList;
    }
}
