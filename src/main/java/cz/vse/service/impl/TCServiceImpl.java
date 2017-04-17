package cz.vse.service.impl;

import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.dto.TCMusterDTO;
import cz.vse.dto.TCMusterList;
import cz.vse.entity.*;
import cz.vse.repository.TCMusterRepository;
import cz.vse.service.*;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TCServiceImpl implements TCService {
    private final Logger l = Logger.getLogger(this.getClass());


    @Autowired
    private MapperFacade mapper;

    @Autowired
    private TSInstanceService tsInstanceService;

    @Autowired
    private TCInstanceService tcInstanceService;

    @Autowired
    private TCMusterService tcMusterService;

    @Autowired
    private TSMusterService tsMusterService;

    @Autowired
    private TCMusterRepository tcMusterRepository;

    @Autowired
    private SuiteService suiteService;

//    public TCInstanceRunDTO runNewTC(long tcMusterId, Person person) {
//        TCInstance tcInstance;
//        TCInstanceRunDTO tcInstanceRunDTO;
//        tcInstance = createAndSaveTCInstanceFromTCMusterId(tcMusterId, person);
//        tcInstanceRunDTO = mapper.map(tcInstance, TCInstanceRunDTO.class);
//        l.info(tcInstanceRunDTO);
//        return tcInstanceRunDTO;
//    }

    public TCInstance runNewTC(long tcMusterId, Person person, String backId, String instanceOrigin) {
        l.info("with: "+ tcMusterId+" and "+person);
        TCInstance tcInstance;
        tcInstance = createAndSaveTCInstanceFromTCMusterId(tcMusterId, person, backId, instanceOrigin);
        l.info("run: "+tcInstance);
        return tcInstance;
    }

//    public TCInstance runNewTC(long tcMusterId, Person person, String instanceOrigin, String backId) {
//        l.info("with: "+ tcMusterId+" and "+person);
//        TCInstance tcInstance;
//        tcInstance = createAndSaveTCInstanceFromTCMusterId(tcMusterId, person, backId, instanceOrigin);
//        l.info("run: "+tcInstance);
//        return tcInstance;
//    }

    /**
     * Mapuje TCMudster na TC instnace. Nahrazuje (obchází orika).
     * ZJISTIT JAK TO UDĚLAT PŘES ORIKU
     *
     * @param tcMuster
     * @return
     */
    public TCInstance mapTCMusterToTCInstance(TCMuster tcMuster) {
        l.info("with: "+ tcMuster);
        TCInstance tcInstanceMapped = new TCInstance();
        tcInstanceMapped.setName(tcMuster.getName());
        tcInstanceMapped.settCMuster(tcMuster);
        l.info("mapped: "+ tcInstanceMapped);
        return tcInstanceMapped;
    }


    public List<TSInstance> mapTSMusterToTSInstance(List<TSMuster> tsMusterList) {
        l.info("with: "+ tsMusterList);
        List<TSInstance> tsInstanceMappedList = new ArrayList<>();
        TSInstance tsInstance;  // = new TSInstance();
        for (TSMuster tsMuster : tsMusterList) {
            tsInstance = new TSInstance();
            l.debug(tsMuster);
            tsInstance.setTsMuster(tsMuster);
            tsInstance.setStatus(StatusEnum.NORUN);
            tsInstanceMappedList.add(tsInstance);
        }
        l.info("mapped: "+tsInstanceMappedList);
        return tsInstanceMappedList;
    }

    private TCInstance createAndSaveTCInstanceFromTCMusterId(long tcMusterId, Person tester, String backId, String instanceOrigin) {
        l.info("with: "+ tcMusterId+" and "+tester);
        TCMuster tcMuster;
        TCInstance tcInstance = new TCInstance();
        List<TSInstance> tsInstanceList = new ArrayList<>();
        tcMuster = tcMusterService.findTestCaseMusterById(tcMusterId);

        tcInstance = mapTCMusterToTCInstance(tcMuster);
        tcInstance.setCreatedDateTime(LocalDateTime.now());
        tcInstance.setTsInstances(tsInstanceList);
        tcInstance.setTester(tester);
        tcInstance.setPrerequisite(tcMuster.getPrerequisite());
        tcInstance.setNote(tcMuster.getNote());
        tcInstance.setBackId(backId);
        tcInstance.setInstanceOrigin(instanceOrigin);

        tcInstanceService.createTestCaseInstance(tcInstance);

        tsInstanceList = createAndSaveTSInstanceFromTCMusterId(tcMuster.getId(), tcInstance);
        l.info("created: "+tcInstance);
        return tcInstance;
    }

    private List<TSInstance> createAndSaveTSInstanceFromTCMusterId(long tcMusterId, TCInstance tcInstance) {
        l.info("with: "+ tcMusterId+" and "+tcInstance);
        TCMuster tcMuster = tcMusterRepository.findOne(tcMusterId);
        List<TSMuster> tsMusterList;
        List<TSInstance> tsInstanceList;
        List<TSInstance> tsInstanceListMapped;
        List<TSInstance> vsechny = new ArrayList<>();
        List<TSInstance> vsechny2 = new ArrayList<>();
////////////////

        tsMusterList = tsMusterService.findAllTestStepMustersByTCMuster(tcMuster);
        tsInstanceList =  mapper.mapAsList(tsMusterList, TSInstance.class);

        mapper.map(tsMusterList, vsechny);
        vsechny2 = mapper.mapAsList(tsMusterList, TSInstance.class);
//        tsInstanceList = mapper.mapAsList(tsMusterList, TSInstance.class);
//        mapper.map(tsMusterList, tsInstanceList);
//        tsInstanceListMapped = mapTSMusterToTSInstance(tsMusterList);
//        mapper.map(tsInstanceList, tsInstanceListMapped);
//        mapper.map(tsInstanceListMapped, tsInstanceList);
        l.info(tsInstanceList);

        for (TSInstance tsInstance : tsInstanceList) {
            tsInstance.settCInstance(tcInstance);
            tsInstanceService.createTestStepInstance(tsInstance);
        }
        l.info(tsInstanceList);
        l.info("created: "+ tsInstanceList);
        return tsInstanceList;

    }

    @Override
    public List<TCMusterDTO> findAllTCMustersDTO() {
        List<TCMusterDTO> tcMusterDTOList;
        List<TCMuster> tcMusterList = tcMusterRepository.findAll();
        tcMusterDTOList = mapper.mapAsList(tcMusterList, TCMusterDTO.class);
        l.info("found: "+ tcMusterDTOList);
        return tcMusterDTOList;
    }

    @Override
    public List<TCMuster> findAllTCMusters() {
        List<TCMuster> tcMusterList;
        tcMusterList = tcMusterRepository.findAll();
        l.info("found: "+tcMusterList);
        return tcMusterList;
    }

    @Override
    public List<TCMusterDTO> findAllTCMustersDTOBySuiteId(Long id) {
        l.info("with: "+ id);
        List<TCMusterDTO> tcMusterDTOList;
        List<TCMuster> tcMusterList;
        TestSuite testSuite = suiteService.findTestSuiteById(id);
        tcMusterList = tcMusterRepository.findAllTCMustersByTestSuitesOrderById(testSuite);
        tcMusterDTOList = mapper.mapAsList(tcMusterList, TCMusterDTO.class);
        l.info("found: "+ tcMusterDTOList);
        return tcMusterDTOList;
    }

    @Override
    public List<TCMusterList> findAllTCMusterListsBySuiteId(Long id) {
        l.info("with: "+ id);
        List<TCMusterList> tcMusterLists;
        List<TCMuster> tcMusterList;
        TestSuite testSuite = suiteService.findTestSuiteById(id);
        tcMusterList = tcMusterRepository.findAllTCMustersByTestSuitesOrderById(testSuite);
        tcMusterLists = mapper.mapAsList(tcMusterList, TCMusterList.class);
        l.info("found: "+ id);
        return tcMusterLists;
    }
}
