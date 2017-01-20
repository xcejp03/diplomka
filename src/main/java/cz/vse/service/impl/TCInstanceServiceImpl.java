package cz.vse.service.impl;

import cz.vse.dto.TCInstanceDTO;
import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.entity.*;
import cz.vse.repository.TCInstanceRepository;
import cz.vse.repository.TCMusterRepository;
import cz.vse.service.TCInstanceService;
import cz.vse.service.TCMusterService;
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
        tcInstance.setStatus(StatusEnum.NORUN);
        tcInstanceRepository.save(tcInstance);
        l.info("created TCInstance - service: " + tcInstance);
    }

    public void updateTestCaseInstance(TCInstance tcInstance) {
        l.debug("updating TCInstance - service");
        tcInstance.setUpdatedDateTime(LocalDateTime.now());
        StatusEnum statusEnum = tcInstance != null ? getTCInstanceStatusFromTSInstancesStatuses(tcInstance) : StatusEnum.NORUN;
        tcInstance.setStatus(statusEnum);
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
        tcInstance = tcInstanceRepository.findTop1ByTCMusterOrderByCreatedDateTimeDesc(tcMuster);
        return tcInstance;
    }

    public TCInstance findLastTCInstanceByTCMusterId(long id) {
        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        TCInstance tcInstance = findLastTCInstanceByTCMuster(tcMuster);
        return tcInstance;
    }

    @Override
    public int getNumberOfTCsInProjectByStatus(Project project, StatusEnum status) {
        int cislo = tcInstanceRepository.getNumberOfTCsInProjectByStatus();
//        int cislo = tcInstanceRepository.getNumberOfTCsInProjectByStatus(project.getId(), status.getPriorityOrder());

        return cislo;
    }


    public void refreshTCInstanceStatus(long id) {
        TCInstance tcInstance = findTestCaseInstanceById(id);
        StatusEnum refreshedStatus = getTCInstanceStatusFromTSInstancesStatuses(tcInstance);
        tcInstance.setStatus(refreshedStatus);
        l.info("refreshed tcInstance: " + tcInstance);
        tcInstanceRepository.save(tcInstance);
    }

    private StatusEnum getTCInstanceStatusFromTSInstancesStatuses(TCInstance tcInstance) {
        List<TSInstance> tsInstanceList = tcInstance.getTsInstances();
        List<StatusEnum> statusEnumList = new ArrayList<>();

        for (TSInstance tsInstance : tsInstanceList) {
            statusEnumList.add(tsInstance.getStatus());
        }

        if (statusEnumList.contains(StatusEnum.FAILED)) {
            return StatusEnum.FAILED;
        }
        if (statusEnumList.contains(StatusEnum.BLOCKED)) {
            return StatusEnum.BLOCKED;
        }

        if (isItAllNorun(statusEnumList)) {
            return StatusEnum.NORUN;
        }
        if (isItAllPassed(statusEnumList)) {
            return StatusEnum.PASSED;
        }

        return StatusEnum.NOTCOMPLETED;
    }

    private boolean isItAllPassed(List<StatusEnum> tsInstanceList) {
        for (StatusEnum statusEnum : tsInstanceList) {
            if (statusEnum != StatusEnum.PASSED) {
                return false;
            }
        }
        return true;
    }

    private boolean isItAllNorun(List<StatusEnum> tsInstanceList) {
        for (StatusEnum statusEnum : tsInstanceList) {
            if (statusEnum != StatusEnum.NORUN) {
                return false;
            }
        }
        return true;
    }

}
