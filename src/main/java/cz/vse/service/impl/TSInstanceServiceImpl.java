package cz.vse.service.impl;

import cz.vse.dao.TestStepInstanceDao;
import cz.vse.dto.TSInstanceRunDTO;
import cz.vse.entity.TSInstance;
import cz.vse.service.TSInstanceService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TSInstanceServiceImpl implements TSInstanceService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    private TestStepInstanceDao testStepInstanceDao;

    @Autowired
    private MapperFacade mapper;

    public void createTestStepInstance(TSInstance tsInstance) {
        l.debug("creating TSInstance - service");
        testStepInstanceDao.saveTestStepInstance(tsInstance);
        l.info("created TSInstance - service: " + tsInstance);
    }

    public void updateTestStepInstance(TSInstance tsInstance) {
        l.debug("updating TSInstance - service");
        testStepInstanceDao.updateTestStepInstance(tsInstance);
        l.info("updated TSInstance - service: " + tsInstance);
    }

    public void updateTestStepInstance(TSInstanceRunDTO tsInstanceRunDTO) {
        l.debug("updating TSInstance - service");
        TSInstance tsInstance = findTestStepInstanceById(tsInstanceRunDTO.getId());

        mapper.map(tsInstanceRunDTO, tsInstance);
        l.info(tsInstance);

//        tsInstance = mapper.map(tsInstanceRunDTO, TSInstance.class);
        testStepInstanceDao.updateTestStepInstance(tsInstance);
        l.info("updated TSInstance - service: " + tsInstance);
    }

    public void deleteTestStepInstance(TSInstance tsInstanceToDelete) {
        l.debug("deleting TSInstance - service");
        testStepInstanceDao.deleteTestStepInstance(tsInstanceToDelete);
        l.info("TSInstance deleted - service: " + tsInstanceToDelete);
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
        TSInstance tsInstance;
        tsInstance = testStepInstanceDao.getTestStepInstanceById(id);
        l.info("TSInstance founf - service: " + id + " - " + tsInstance);
        return tsInstance;
    }

    public TSInstanceRunDTO findTestStepInstanceRunDTOById(long id) {
        l.debug("finding TSInstance - service");
        TSInstance tsInstance = testStepInstanceDao.getTestStepInstanceById(id);
        TSInstanceRunDTO tsInstanceRunDTO = mapper.map(tsInstance, TSInstanceRunDTO.class);
        l.info("TSInstance founf - service: " + id + " - " + tsInstanceRunDTO);
        return tsInstanceRunDTO;
    }

    public List<TSInstance> findAllTestStepInstances() {
        l.debug("finding all testStepInstances - service");
        List<TSInstance> tsInstanceList;
        tsInstanceList = testStepInstanceDao.getAllTestStepInstances();
        l.info("found all testStepInstances - service: " + tsInstanceList.toString());
        return tsInstanceList;
    }
}
