package cz.vse.service;

import cz.vse.dao.TestStepInstanceDao;
import cz.vse.entity.TestStepInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TestStepInstanceService {
    private final Logger l = Logger.getLogger(this.getClass());
    
    @Autowired
    private TestStepInstanceDao testStepInstanceDao;

    public void createTestStepInstance(TestStepInstance testStepInstance) {
        l.debug("creating testStepInstance - service");
        testStepInstanceDao.saveTestStepInstance(testStepInstance);
        l.info("created testStepInstance - service: " + testStepInstance);
    }

    public void updateTestStepInstance(TestStepInstance testStepInstance) {
        l.debug("updating testStepInstance - service");
        testStepInstanceDao.updateTestStepInstance(testStepInstance);
        l.info("updated testStepInstance - service: " + testStepInstance);
    }

    public void deleteTestStepInstance(TestStepInstance testStepInstanceToDelete) {
        l.debug("deleting testStepInstance - service");
        testStepInstanceDao.deleteTestStepInstance(testStepInstanceToDelete);
        l.info("testStepInstance deleted - service: " + testStepInstanceToDelete);
    }

    public void deleteTestStepInstanceById(long testStepInstanceToDeleteById) {
        l.debug("deleting testStepInstance - service");
        TestStepInstance testStepInstanceToDelete;
        testStepInstanceToDelete = testStepInstanceDao.getTestStepInstanceById(testStepInstanceToDeleteById);
        testStepInstanceDao.deleteTestStepInstance(testStepInstanceToDelete);
        l.info("testStepInstance deleted - service: " + testStepInstanceToDelete);
    }

    public TestStepInstance findTestStepInstanceById(long id) {
        l.debug("finding testStepInstance - service");
        TestStepInstance testStepInstance;
        testStepInstance = testStepInstanceDao.getTestStepInstanceById(id);
        l.info("testStepInstance founf - service: " + id + " - " + testStepInstance);
        return testStepInstance;
    }

    public List<TestStepInstance> findAllTestStepInstances() {
        l.debug("finding all testStepInstances - service");
        List<TestStepInstance> testStepInstanceList;
        testStepInstanceList = testStepInstanceDao.getAllTestStepInstances();
        l.info("found all testStepInstances - service: " + testStepInstanceList.toString());
        return testStepInstanceList;
    }
}
