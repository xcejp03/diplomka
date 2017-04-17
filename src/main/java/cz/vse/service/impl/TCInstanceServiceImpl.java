package cz.vse.service.impl;

import cz.vse.dto.TCInstanceDTO;
import cz.vse.dto.TCInstanceList;
import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.entity.*;
import cz.vse.repository.TCInstanceRepository;
import cz.vse.service.TCInstanceService;
import cz.vse.service.TCMusterService;
import cz.vse.service.WorkTCService;
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
    @Autowired
    private WorkTCService workTCService;

    public void createTestCaseInstance(TCInstance tcInstance) {
        l.info("with: " + tcInstance);
        tcInstance.setStatus(StatusEnum.NORUN);
        tcInstanceRepository.save(tcInstance);
        l.info("created: " + tcInstance);
    }

    public void updateTestCaseInstance(TCInstance tcInstance) {
        l.info("with: " + tcInstance);
        tcInstance.setUpdatedDateTime(LocalDateTime.now());
        StatusEnum statusEnum = tcInstance != null ? getTCInstanceStatusFromTSInstancesStatuses(tcInstance) : StatusEnum.NORUN;
        tcInstance.setStatus(statusEnum);
        tcInstanceRepository.save(tcInstance);
        l.info("updated: " + tcInstance);
    }

    public void deleteTestCaseInstance(TCInstance tcInstanceToDelete) {
        l.info("with: " + tcInstanceToDelete);
        tcInstanceRepository.delete(tcInstanceToDelete);
        l.info("deleted: " + tcInstanceToDelete);
    }

    public void deleteTestCaseInstanceById(long testCaseInstanceToDeleteById) {
        l.info("with: " + testCaseInstanceToDeleteById);
        tcInstanceRepository.delete(testCaseInstanceToDeleteById);
        l.info("deleted: " + testCaseInstanceToDeleteById);
    }

    public TCInstance findTestCaseInstanceById(long id) {
        l.info("with: " + id);
        TCInstance tcInstance;
        tcInstance = tcInstanceRepository.findOne(id);
        l.info("found: " + tcInstance);
        return tcInstance;
    }

    public List<TCInstance> findAllTestCaseInstances() {
        List<TCInstance> TCInstanceList;
        TCInstanceList = tcInstanceRepository.findAll();
        l.info("found: " + TCInstanceList.toString());
        return TCInstanceList;
    }


    public List<TCInstance> findAllTCInstancesByTCMusterId(long id) {
        l.info("with: " + id);
        List<TCInstance> tcInstanceList;
        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        tcInstanceList = tcInstanceRepository.findByTCMusterOrderById(tcMuster);
        l.info("found: " + tcInstanceList);
        return tcInstanceList;
    }

    @Override
    public List<TCInstanceDTO> findAllTCInstancesDTOByTCMusterId(long id) {
        l.info("with: " + id);
        List<TCInstance> tcInstanceList;
        List<TCInstanceDTO> tcInstanceDTOList;

        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        tcInstanceList = tcInstanceRepository.findByTCMusterOrderById(tcMuster);
        tcInstanceDTOList = mapper.mapAsList(tcInstanceList, TCInstanceDTO.class);
        l.info("found: " + tcInstanceDTOList);
        return tcInstanceDTOList;
    }

    @Override
    public List<TCInstanceList> findAllTCInstanceListsByTCMusterId(long id) {
        l.info("with: " + id);
        List<TCInstance> tcInstanceList;
        List<TCInstanceList> tcInstanceLists;

        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        tcInstanceList = tcInstanceRepository.findByTCMusterOrderById(tcMuster);
        tcInstanceLists = mapper.mapAsList(tcInstanceList, TCInstanceList.class);
        l.info("found: " + tcInstanceLists);
        return tcInstanceLists;
    }

    @Override
    public List<TCInstanceDTO> findTCInstancesDTOByWorkTCId(long id) {
        l.info("with: " + id);
        List<TCInstance> tcInstanceList;
        List<TCInstanceDTO> tcInstanceDTOList;

        WorkTC workTC = workTCService.findWorkTCById(id);
        tcInstanceList = tcInstanceRepository.findByWorkTCOrderById(workTC);
        tcInstanceDTOList = mapper.mapAsList(tcInstanceList, TCInstanceDTO.class);
        l.info("found: " + tcInstanceDTOList);
        return tcInstanceDTOList;
    }

    @Override
    public List<TCInstanceList> findTCInstanceListsByWorkTCId(long id) {
        l.info("with: " + id);
        List<TCInstance> tcInstanceList;
        List<TCInstanceList> tcInstanceLists;

        WorkTC workTC = workTCService.findWorkTCById(id);
        tcInstanceList = tcInstanceRepository.findByWorkTCOrderById(workTC);
        tcInstanceLists = mapper.mapAsList(tcInstanceList, TCInstanceList.class);
        l.info("found: " + tcInstanceLists);
        return tcInstanceLists;
    }

    public TCInstanceRunDTO findTCInstanceRunDTOById(long id) {
        l.info("with: " + id);
        TCInstance tcInstance = findTestCaseInstanceById(id);
        TCInstanceRunDTO tcInstanceRunDTO = mapper.map(tcInstance, TCInstanceRunDTO.class);
        l.info("found: " + tcInstanceRunDTO);
        return tcInstanceRunDTO;
    }

    public TCInstance findLastTCInstanceByTCMuster(TCMuster tcMuster) {
        l.info("with: " + tcMuster);
        TCInstance tcInstance;
        tcInstance = tcInstanceRepository.findTop1ByTCMusterOrderByCreatedDateTimeDesc(tcMuster);
        l.info("found: " + tcInstance);
        return tcInstance;
    }

    public TCInstance findLastTCInstanceByTCMusterId(long id) {
        l.info("with: " + id);
        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        TCInstance tcInstance = findLastTCInstanceByTCMuster(tcMuster);
        l.info("found: " + tcInstance);
        return tcInstance;
    }

    @Override
    public int getNumberOfTCsInProjectByStatus(Project project, StatusEnum status) {
        l.info("with: " + project + " and " + status);
        int number = tcInstanceRepository.getNumberOfTCsInProjectByStatus(status, project.getId());
        l.info("getted: " + number);
        return number;
    }

    public void refreshTCInstanceStatus(long id) {
        l.info("with: " + id);
        TCInstance tcInstance = findTestCaseInstanceById(id);
        StatusEnum refreshedStatus = getTCInstanceStatusFromTSInstancesStatuses(tcInstance);
        tcInstance.setStatus(refreshedStatus);
        l.info("refreshed tcInstance: " + tcInstance);
        tcInstanceRepository.save(tcInstance);
    }

    private StatusEnum getTCInstanceStatusFromTSInstancesStatuses(TCInstance tcInstance) {
        l.info("with: " + tcInstance);
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
