package cz.vse.service;

import cz.vse.dao.TestCaseInstanceDao;
import cz.vse.entity.TCInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TCInstanceService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private TestCaseInstanceDao testCaseInstanceDao;

    public void createTestCaseInstance(TCInstance TCInstance) {
        l.debug("creating TCInstance - service");
        testCaseInstanceDao.saveTestCaseInstance(TCInstance);
        l.info("created TCInstance - service: " + TCInstance);
    }

    public void updateTestCaseInstance(TCInstance TCInstance) {
        l.debug("updating TCInstance - service");
        testCaseInstanceDao.updateTestCaseInstance(TCInstance);
        l.info("updated TCInstance - service: " + TCInstance);
    }

    public void deleteTestCaseInstance(TCInstance TCInstanceToDelete) {
        l.debug("deleting TCInstance - service");
        testCaseInstanceDao.deleteTestCaseInstance(TCInstanceToDelete);
        l.info("TCInstance deleted - service: " + TCInstanceToDelete);
    }

    public void deleteTestCaseInstanceById(long testCaseInstanceToDeleteById) {
        l.debug("deleting TCInstance - service");
        TCInstance TCInstanceToDelete;
        TCInstanceToDelete = testCaseInstanceDao.getTestCaseInstanceById(testCaseInstanceToDeleteById);
        testCaseInstanceDao.deleteTestCaseInstance(TCInstanceToDelete);
        l.info("TCInstance deleted - service: " + TCInstanceToDelete);
    }

    public TCInstance findTestCaseInstanceById(long id) {
        l.debug("finding TCInstance - service");
        TCInstance TCInstance;
        TCInstance = testCaseInstanceDao.getTestCaseInstanceById(id);
        l.info("TCInstance founf - service: " + id + " - " + TCInstance);
        return TCInstance;
    }

    public List<TCInstance> findAllTestCaseInstances() {
        l.debug("finding all testCaseInstances - service");
        List<TCInstance> TCInstanceList;
        TCInstanceList = testCaseInstanceDao.getAllTestCaseInstances();
        l.info("found all testCaseInstances - service: " + TCInstanceList.toString());
        return TCInstanceList;
    }
}
