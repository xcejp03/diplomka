package cz.vse.service.impl;

import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;
import cz.vse.entity.TSInstance;
import cz.vse.entity.TSMuster;
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

    public TCInstanceRunDTO runNewTC(long tcMusterId) {
        TCInstance tcInstance;
        TCInstanceRunDTO tcInstanceRunDTO;

        tcInstance = createAndSaveTCInstanceFromTCMusterId(tcMusterId);

        tcInstanceRunDTO = mapper.map(tcInstance, TCInstanceRunDTO.class);
        l.info(tcInstanceRunDTO);
        return tcInstanceRunDTO;
    }

    /**
     * Mapuje TCMudster na TC instnace. Nahrazuje (obchází orika).
     * ZJISTIT JAK TO UDĚLAT PŘES ORIKU
     *
     * @param tcMuster
     * @return
     */
    public TCInstance mapTCMusterToTCInstance(TCMuster tcMuster) {
        TCInstance tcInstanceMapped = new TCInstance();
        tcInstanceMapped.setName(tcMuster.getName());
        tcInstanceMapped.settCMuster(tcMuster);
        return tcInstanceMapped;
    }

    public List<TSInstance> mapTSMusterToTSInstance(List<TSMuster> tsMusterList) {
        List<TSInstance> tsInstanceMappedList = new ArrayList<>();
        TSInstance tsInstance;  // = new TSInstance();
        for (TSMuster tsMuster : tsMusterList) {
            tsInstance = new TSInstance();
            l.debug(tsMuster);
            tsInstance.setTsMuster(tsMuster);
            tsInstanceMappedList.add(tsInstance);
        }
        return tsInstanceMappedList;
    }

    public TCInstance createAndSaveTCInstanceFromTCMusterId(long tcMusterId) {
        TCMuster tcMuster;
        TCInstance tcInstance = new TCInstance();
        List<TSInstance> tsInstanceList = new ArrayList<>();

        tcMuster = tcMusterService.findTestCaseMusterById(tcMusterId);

        tcInstance = mapTCMusterToTCInstance(tcMuster);
        tcInstance.setCreatedDateTime(LocalDateTime.now());
        tcInstance.setTsInstances(tsInstanceList);

        tcInstanceService.createTestCaseInstance(tcInstance);

        tsInstanceList = createAndSaveTSInstanceFromTCMusterId(tcMuster.getId(), tcInstance);

        return tcInstance;
    }

    public List<TSInstance> createAndSaveTSInstanceFromTCMusterId(long tcMusterId, TCInstance tcInstance) {
        TCMuster tcMuster = tcMusterRepository.findOne(tcMusterId);
        List<TSMuster> tsMusterList;
        List<TSInstance> tsInstanceList = null;

        tsMusterList = tsMusterService.findAllTestStepMustersByTCMuster(tcMuster);
        tsInstanceList = mapTSMusterToTSInstance(tsMusterList);
        tsInstanceList = mapper.mapAsList(tsMusterList, TSInstance.class);

        for (TSInstance tsInstance : tsInstanceList) {
            tsInstance.settCInstance(tcInstance);
            tsInstanceService.createTestStepInstance(tsInstance);
        }

        l.info(tsInstanceList);
        return tsInstanceList;
    }
}
