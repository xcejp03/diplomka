package cz.vse.service;

import cz.vse.dao.TestCaseInstanceDao;
import cz.vse.dao.TestStepInstanceDao;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TSInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TCInstanceService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private TestCaseInstanceDao testCaseInstanceDao;

    @Autowired
    private TestStepInstanceDao testStepInstanceDao;

    @Autowired
    private TCMusterService tcMusterService;

    public void createTestCaseInstance(TCInstance tcInstance) {
        l.debug("creating TCInstance - service");

        testCaseInstanceDao.saveTestCaseInstance(tcInstance);
        l.info("created TCInstance - service: " + tcInstance);
    }

    public void updateTestCaseInstance(TCInstance tcInstance) {
        l.debug("updating TCInstance - service");
        testCaseInstanceDao.updateTestCaseInstance(tcInstance);
        l.info("updated TCInstance - service: " + tcInstance);
    }

    public void deleteTestCaseInstance(TCInstance tcInstanceToDelete) {
        l.debug("deleting TCInstance - service");
        testCaseInstanceDao.deleteTestCaseInstance(tcInstanceToDelete);
        l.info("TCInstance deleted - service: " + tcInstanceToDelete);
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
        TCInstance tcInstance;
        tcInstance = testCaseInstanceDao.getTestCaseInstanceById(id);
        l.info("TCInstance founf - service: " + id + " - " + tcInstance);
        return tcInstance;
    }

    public List<TCInstance> findAllTestCaseInstances() {
        l.debug("finding all testCaseInstances - service");
        List<TCInstance> TCInstanceList;
        TCInstanceList = testCaseInstanceDao.getAllTestCaseInstances();
        l.info("found all testCaseInstances - service: " + TCInstanceList.toString());
        return TCInstanceList;
    }

    /**
     * přendat do TS!!!!!!
     *
     * @param id
     * @return
     */
    public List<TSInstance> findAllTSInstancesByTCInstanceId(long id) {
        List<TSInstance> tsInstanceList = new ArrayList<>();
        tsInstanceList = testStepInstanceDao.getAllTestStepInstancesByTCInstanceId(id);

        return tsInstanceList;
    }

    public List<TCInstance> findAllTCInstancesByTCMusterId(long id) {
        List<TCInstance> tcInstanceList;
        tcInstanceList = testCaseInstanceDao.getAllTCInstancesByTCMusterId(id);
        return tcInstanceList;
    }
}
