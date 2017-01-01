package cz.vse.service.impl;

import cz.vse.dto.TCMusterDTO;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;
import cz.vse.entity.TestSuite;
import cz.vse.repository.TCMusterRepository;
import cz.vse.service.SuiteService;
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
public class TCMusterServiceImpl implements TCMusterService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private TCMusterRepository tcMusterRepository;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private SuiteService suiteService;

    @Autowired
    private TCInstanceService tcInstanceService;

    public void createTestCaseMuster(TCMuster tcMuster) {
        l.debug("creating TCMuster - service");
        tcMusterRepository.save(tcMuster);
        l.info("created TCMuster - service: " + tcMuster);
    }

    public void createTestCaseMuster(TCMusterDTO tcMusterDTO) {
        l.debug("creating TCMuster - service");
        TCMuster tcMuster;
        tcMuster = mapper.map(tcMusterDTO, TCMuster.class);
        tcMuster.setCreatedDateTime(LocalDateTime.now());
        tcMusterRepository.save(tcMuster);
        l.info("created TCMuster - service: " + tcMusterDTO);
    }

    public void updateTestCaseMuster(TCMuster tcMuster) {
        l.debug("updating TCMuster - service");
        tcMuster.setUpdatedDateTime(LocalDateTime.now());
        tcMusterRepository.save(tcMuster);
        l.info("updated TCMuster - service: " + tcMuster);
    }

    public void updateTestCaseMuster(TCMusterDTO tcMusterDTO) {
        l.debug("updating TCMuster - service");
        TCMuster tcMuster = tcMusterRepository.findOne(tcMusterDTO.getId());
        mapper.map(tcMusterDTO, tcMuster);
        tcMuster.setUpdatedDateTime(LocalDateTime.now());
        tcMusterRepository.save(tcMuster);
        l.info("updated TCMuster - service: " + tcMuster);
    }

    public void deleteTestCaseMuster(TCMuster TCMusterToDelete) {
        l.debug("deleting TCMuster - service");
        tcMusterRepository.delete(TCMusterToDelete);
        l.info("TCMuster deleted - service: " + TCMusterToDelete);
    }

    public void deleteTestCaseMusterById(long testCaseMusterToDeleteById) {
        l.debug("deleting TCMuster - service");
        TCMuster TCMusterToDelete;
        tcMusterRepository.delete(testCaseMusterToDeleteById);
        l.info("TCMuster deleted - service: " + testCaseMusterToDeleteById);
    }

    public TCMuster findTestCaseMusterById(long id) {
        l.debug("finding TCMuster - service");
        TCMuster tcMuster;
        tcMuster = tcMusterRepository.findOne(id);
        l.info("TCMuster found - service: " + id + " - " + tcMuster);
        return tcMuster;
    }

    public TCMusterDTO findTestCaseMusterDTOById(long id) {
        l.debug("finding TCMusterDTO - service");
        TCMuster tcMuster;
        TCMusterDTO tcMusterDTO;
        tcMuster = tcMusterRepository.findOne(id);
        tcMusterDTO = mapper.map(tcMuster, TCMusterDTO.class);
        l.info("TCMuster found - service: " + id + " - " + tcMusterDTO);
        return tcMusterDTO;
    }

    public List<TCMuster> findAllTestCaseMusters() {
        l.debug("finding all testCaseMusters - service");
        List<TCMuster> TCMusterList;
        TCMusterList = tcMusterRepository.findAll();
        l.info("found all testCaseMusters - service: ");
        return TCMusterList;
    }

    public List<TCMusterDTO> findAllTestCaseMustersDTO() {
        l.debug("finding all testCaseMustersDTO - service");
        List<TCMuster> tcMusterList;
        List<TCMusterDTO> tcMusterDTOList;
        tcMusterList = tcMusterRepository.findAll();
        tcMusterDTOList = mapper.mapAsList(tcMusterList, TCMusterDTO.class);
        l.info("found all testCaseMusters - service: ");
        return tcMusterDTOList;
    }

    public TCMuster findTestCaseMusterByTCInstanceId(long tcInstanceId) {
        TCInstance tcInstance;
        TCMuster tcMuster;
        tcInstance = tcInstanceService.findTestCaseInstanceById(tcInstanceId);
        tcMuster = findTestCaseMusterById(tcInstance.gettCMuster().getId());
        return tcMuster;
    }

    @Override
    public List<TCMuster> findAllTestCaseMustersByTestSuite(TestSuite testSuite) {
        List<TCMuster> tcMusterList;
        tcMusterList = tcMusterRepository.findAllTCMustersDTOByTestSuites(testSuite);
        return tcMusterList;
    }

    @Override
    public List<TCMusterDTO> findAllTestCaseMustersDTOByTestSuiteId(long id) {
        TestSuite testSuite;
        List<TCMuster> tcMusterList;
        List<TCMusterDTO> tcMusterDTOList;
        testSuite = suiteService.findTestSuiteById(id);
        tcMusterList = findAllTestCaseMustersByTestSuite(testSuite);
        tcMusterDTOList = mapper.mapAsList(tcMusterList, TCMusterDTO.class);
        return tcMusterDTOList;
    }
}
