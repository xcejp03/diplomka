package cz.vse.service;

import cz.vse.dao.TestStepMusterDao;
import cz.vse.entity.TSMuster;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TSMusterService {
    private final Logger l = Logger.getLogger(this.getClass());
    
    @Autowired
    TestStepMusterDao testStepMusterDao;

    public void createTestStepMuster(TSMuster TSMuster) {
        l.debug("creating TSMuster - service");
        testStepMusterDao.saveTestStepMuster(TSMuster);
        l.info("created TSMuster - service: " + TSMuster);
    }

    public void updateTestStepMuster(TSMuster TSMuster) {
        l.debug("updating TSMuster - service");
        testStepMusterDao.updateTestStepMuster(TSMuster);
        l.info("updated TSMuster - service: " + TSMuster);
    }

    public void deleteTestStepMuster(TSMuster TSMusterToDelete) {
        l.debug("deleting TSMuster - service");
        testStepMusterDao.deleteTestStepMuster(TSMusterToDelete);
        l.info("TSMuster deleted - service: " + TSMusterToDelete);
    }

    public void deleteTestStepMusterById(long testStepMusterToDeleteById) {
        l.debug("deleting TSMuster - service");
        TSMuster TSMusterToDelete;
        TSMusterToDelete = testStepMusterDao.getTestStepMusterById(testStepMusterToDeleteById);
        testStepMusterDao.deleteTestStepMuster(TSMusterToDelete);
        l.info("TSMuster deleted - service: " + TSMusterToDelete);
    }

    public TSMuster findTestStepMusterById(long id) {
        l.debug("finding TSMuster - service");
        TSMuster TSMuster;
        TSMuster = testStepMusterDao.getTestStepMusterById(id);
        l.info("TSMuster founf - service: " + id + " - " + TSMuster);
        return TSMuster;
    }

    public List<TSMuster> findAllTestStepMusters() {
        l.debug("finding all testStepMusters - service");
        List<TSMuster> TSMusterList;
        TSMusterList = testStepMusterDao.getAllTestStepMusters();
        l.info("found all testStepMusters - service: " + TSMusterList.toString());
        return TSMusterList;
    }
    
}
