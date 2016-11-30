package cz.vse.service.impl;

import cz.vse.dao.TestCaseMusterDao;
import cz.vse.dto.TCMusterDTO;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;
import cz.vse.service.TCInstanceService;
import cz.vse.service.TCMusterService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TCMusterServiceImpl implements TCMusterService{
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private TestCaseMusterDao testCaseMusterDao;

    @Autowired
    private MapperFacade mapper;



    @Autowired
    private TCInstanceService tcInstanceService;

    public void createTestCaseMuster(TCMuster tcMuster) {
        l.debug("creating TCMuster - service");
        testCaseMusterDao.saveTestCaseMuster(tcMuster);
        l.info("created TCMuster - service: " + tcMuster);
    }

    public void createTestCaseMuster(TCMusterDTO tcMusterDTO) {
        l.debug("creating TCMuster - service");
        TCMuster tcMuster;
        tcMuster = mapper.map(tcMusterDTO, TCMuster.class);
        tcMuster.setCreatedDateTime(LocalDateTime.now());
        testCaseMusterDao.saveTestCaseMuster(tcMuster);
        l.info("created TCMuster - service: " + tcMusterDTO);
    }

    public void updateTestCaseMuster(TCMuster tcMuster) {
        l.debug("updating TCMuster - service");
        testCaseMusterDao.updateTestCaseMuster(tcMuster);
        l.info("updated TCMuster - service: " + tcMuster);
    }

    public void updateTestCaseMuster(TCMusterDTO tcMusterDTO) {
        l.debug("updating TCMuster - service");
        TCMuster tcMuster;
        tcMuster = mapper.map(tcMusterDTO, TCMuster.class);
        testCaseMusterDao.updateTestCaseMuster(tcMuster);
        l.info("updated TCMuster - service: " + tcMuster);
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
        TCMuster tcMuster;
        tcMuster = testCaseMusterDao.getTestCaseMusterById(id);
        l.info("TCMuster found - service: " + id + " - " + tcMuster);
        return tcMuster;
    }

    public TCMusterDTO findTestCaseMusterDTOById(long id) {
        l.debug("finding TCMusterDTO - service");
        TCMuster tcMuster;
        TCMusterDTO tcMusterDTO;
        tcMuster = testCaseMusterDao.getTestCaseMusterById(id);
        tcMusterDTO = mapper.map(tcMuster, TCMusterDTO.class);
        l.info("TCMuster found - service: " + id + " - " + tcMusterDTO);
        return tcMusterDTO;
    }

    public List<TCMuster> findAllTestCaseMusters() {
        l.debug("finding all testCaseMusters - service");
        List<TCMuster> TCMusterList;
        TCMusterList = testCaseMusterDao.getAllTestCaseMusters();
        l.info("found all testCaseMusters - service: ");
        return TCMusterList;
    }

    public List<TCMusterDTO> findAllTestCaseMustersDTO() {
        l.debug("finding all testCaseMustersDTO - service");
        List<TCMuster> tcMusterList;
        List<TCMusterDTO> tcMusterDTOList;
        tcMusterList = testCaseMusterDao.getAllTestCaseMusters();
        tcMusterDTOList = mapper.mapAsList(tcMusterList, TCMusterDTO.class);
        l.info("found all testCaseMusters - service: ");
        return tcMusterDTOList;
    }

    public TCMuster findTestCaseMusterByTCInstanceId(long tcInstanceId) {
        TCInstance tcInstance;
        TCMuster tcMuster;
        tcInstance = tcInstanceService.findTestCaseInstanceById(tcInstanceId);
        tcMuster = findTestCaseMusterById(tcInstance.getTcMuster().getId());
        return tcMuster;
    }
}
