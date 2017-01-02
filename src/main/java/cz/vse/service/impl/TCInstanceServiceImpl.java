package cz.vse.service.impl;

import cz.vse.dto.TCInstanceDTO;
import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;
import cz.vse.repository.TCInstanceRepository;
import cz.vse.repository.TCMusterRepository;
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
public class TCInstanceServiceImpl implements TCInstanceService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    TCMusterService tcMusterService;

    @Autowired
    TCInstanceRepository tcInstanceRepository;

    @Autowired
    private MapperFacade mapper;

    public void createTestCaseInstance(TCInstance tcInstance) {
        l.debug("creating TCInstance - service");

        tcInstanceRepository.save(tcInstance);
        l.info("created TCInstance - service: " + tcInstance);
    }

    public void updateTestCaseInstance(TCInstance tcInstance) {
        l.debug("updating TCInstance - service");
        tcInstance.setUpdatedDateTime(LocalDateTime.now());
        tcInstanceRepository.save(tcInstance);
        l.info("updated TCInstance - service: " + tcInstance);
    }

    public void deleteTestCaseInstance(TCInstance tcInstanceToDelete) {
        l.debug("deleting TCInstance - service");
        tcInstanceRepository.delete(tcInstanceToDelete);
        l.info("TCInstance deleted - service: " + tcInstanceToDelete);
    }

    public void deleteTestCaseInstanceById(long testCaseInstanceToDeleteById) {
        l.debug("deleting TCInstance - service");
        tcInstanceRepository.delete(testCaseInstanceToDeleteById);
        l.info("TCInstance deleted - service: " + testCaseInstanceToDeleteById);
    }

    public TCInstance findTestCaseInstanceById(long id) {
        l.debug("finding TCInstance - service");
        TCInstance tcInstance;
        tcInstance = tcInstanceRepository.findOne(id);
        l.info("TCInstance founf - service: " + id + " - " + tcInstance);
        return tcInstance;
    }

    public List<TCInstance> findAllTestCaseInstances() {
        l.debug("finding all testCaseInstances - service");
        List<TCInstance> TCInstanceList;
        TCInstanceList = tcInstanceRepository.findAll();
        l.info("found all testCaseInstances - service: " + TCInstanceList.toString());
        return TCInstanceList;
    }


    public List<TCInstance> findAllTCInstancesByTCMusterId(long id) {
        List<TCInstance> tcInstanceList;
        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        tcInstanceList = tcInstanceRepository.findByTCMusterOrderById(tcMuster);
        return tcInstanceList;
    }

    @Override
    public List<TCInstanceDTO> findAllTCInstancesDTOByTCMusterId(long id) {
        List<TCInstance> tcInstanceList;
        List<TCInstanceDTO> tcInstanceDTOList;

        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        tcInstanceList = tcInstanceRepository.findByTCMusterOrderById(tcMuster);
        tcInstanceDTOList = mapper.mapAsList(tcInstanceList, TCInstanceDTO.class);
        return tcInstanceDTOList;
    }

    public TCInstanceRunDTO findTCInstanceRunDTOById(long id) {
        TCInstance tcInstance = findTestCaseInstanceById(id);
        TCInstanceRunDTO tcInstanceRunDTO = mapper.map(tcInstance, TCInstanceRunDTO.class);
        return tcInstanceRunDTO;
    }

    public TCInstance findLastTCInstanceByTCMuster(TCMuster tcMuster) {
        TCInstance tcInstance;
        tcInstance = tcInstanceRepository.findTop1ByTCMusterOrderByIdDesc(tcMuster);
        return tcInstance;
    }

    public TCInstance findLastTCInstanceByTCMusterId(long id) {
        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        TCInstance tcInstance = findLastTCInstanceByTCMuster(tcMuster);
        return tcInstance;
    }
}
