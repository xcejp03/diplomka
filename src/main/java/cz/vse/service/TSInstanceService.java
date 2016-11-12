package cz.vse.service;

import cz.vse.dao.TestStepInstanceDao;
import cz.vse.entity.TSInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TSInstanceService {
    private final Logger l = Logger.getLogger(this.getClass());
    
    @Autowired
    private TestStepInstanceDao testStepInstanceDao;

    public void createTestStepInstance(TSInstance TSInstance) {
        l.debug("creating TSInstance - service");
        testStepInstanceDao.saveTestStepInstance(TSInstance);
        l.info("created TSInstance - service: " + TSInstance);
    }

    public void updateTestStepInstance(TSInstance TSInstance) {
        l.debug("updating TSInstance - service");
        testStepInstanceDao.updateTestStepInstance(TSInstance);
        l.info("updated TSInstance - service: " + TSInstance);
    }

    public void deleteTestStepInstance(TSInstance TSInstanceToDelete) {
        l.debug("deleting TSInstance - service");
        testStepInstanceDao.deleteTestStepInstance(TSInstanceToDelete);
        l.info("TSInstance deleted - service: " + TSInstanceToDelete);
    }

    public void deleteTestStepInstanceById(long testStepInstanceToDeleteById) {
        l.debug("deleting TSInstance - service");
        TSInstance TSInstanceToDelete;
        TSInstanceToDelete = testStepInstanceDao.getTestStepInstanceById(testStepInstanceToDeleteById);
        testStepInstanceDao.deleteTestStepInstance(TSInstanceToDelete);
        l.info("TSInstance deleted - service: " + TSInstanceToDelete);
    }

    public TSInstance findTestStepInstanceById(long id) {
        l.debug("finding TSInstance - service");
        TSInstance TSInstance;
        TSInstance = testStepInstanceDao.getTestStepInstanceById(id);
        l.info("TSInstance founf - service: " + id + " - " + TSInstance);
        return TSInstance;
    }

    public List<TSInstance> findAllTestStepInstances() {
        l.debug("finding all testStepInstances - service");
        List<TSInstance> TSInstanceList;
        TSInstanceList = testStepInstanceDao.getAllTestStepInstances();
        l.info("found all testStepInstances - service: " + TSInstanceList.toString());
        return TSInstanceList;
    }
}
