package cz.vse.service.impl;

import cz.vse.dto.TSInstanceList;
import cz.vse.dto.TSInstanceRunDTO;
import cz.vse.entity.StatusEnum;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TSInstance;
import cz.vse.repository.TCInstanceRepository;
import cz.vse.repository.TSInstanceRepository;
import cz.vse.service.TCInstanceService;
import cz.vse.service.TSInstanceService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TSInstanceServiceImpl implements TSInstanceService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    private TSInstanceRepository tsInstanceRepository;

    @Autowired
    private TCInstanceRepository tcInstanceRepository;

    @Autowired
    private TCInstanceService tcInstanceService;


    @Autowired
    private MapperFacade mapper;

    public void createTestStepInstance(TSInstance tsInstance) {
        l.info("with: " + tsInstance);
        tsInstance.setCreatedDateTime(LocalDateTime.now());
        tsInstance.setStatus(StatusEnum.NORUN);
        tsInstanceRepository.save(tsInstance);
        l.info("created: " + tsInstance);
    }

    public void updateTestStepInstance(TSInstance tsInstance) {
        l.info("with: " + tsInstance);
        tsInstance.setUpdatedDateTime(LocalDateTime.now());
        tsInstanceRepository.save(tsInstance);
        tcInstanceService.refreshTCInstanceStatus(tsInstance.gettCInstance().getId());
        l.info("updated: " + tsInstance);
    }

    public void updateTestStepInstance(TSInstanceRunDTO tsInstanceRunDTO) {
        l.info("with: " + tsInstanceRunDTO);
        TSInstance tsInstance = findTestStepInstanceById(tsInstanceRunDTO.getId());

        mapper.map(tsInstanceRunDTO, tsInstance);
        tsInstance.setUpdatedDateTime(LocalDateTime.now());
        l.info(tsInstance);
        tsInstanceRepository.save(tsInstance);
        tcInstanceService.refreshTCInstanceStatus(tsInstance.gettCInstance().getId());
        l.info("updated: " + tsInstance);
    }

    public void deleteTestStepInstance(TSInstance tsInstanceToDelete) {
        l.info("with: " + tsInstanceToDelete);
        tsInstanceRepository.save(tsInstanceToDelete);
        l.info("deleted: " + tsInstanceToDelete);
    }

    public void deleteTestStepInstanceById(long testStepInstanceToDeleteById) {
        l.info("with: " + testStepInstanceToDeleteById);
        TSInstance TSInstanceToDelete;
        tsInstanceRepository.delete(testStepInstanceToDeleteById);
        l.info("deleted: " + testStepInstanceToDeleteById);
    }

    public TSInstance findTestStepInstanceById(long id) {
        l.info("with: " + id);
        TSInstance tsInstance;
        tsInstance = tsInstanceRepository.findOne(id);
        l.info("found: " + tsInstance);
        return tsInstance;
    }

    public List<TSInstance> findAllTSInstancesByTCInstanceId(Long id) {
        l.info("with: " + id);
        List<TSInstance> tsInstanceList;

        TCInstance tcInstance = tcInstanceRepository.findOne(id);
        tsInstanceList = tsInstanceRepository.findAllTestStepInstancesByTCInstanceOrderById(tcInstance);
        l.info("found: " + tsInstanceList);
        return tsInstanceList;
    }

    @Override
    public List<TSInstanceList> findAllTSInstanceListsByTCInstanceId(Long id) {
        l.info("with: " + id);
        List<TSInstanceList> tsInstanceLists;
        TCInstance tcInstance = tcInstanceRepository.findOne(id);
        List<TSInstance> tsInstances = tsInstanceRepository.findAllTestStepInstancesByTCInstanceOrderById(tcInstance);
        tsInstanceLists = mapper.mapAsList(tsInstances, TSInstanceList.class);
        l.info("found: " + tsInstanceLists);
        return tsInstanceLists;
    }

    public TSInstanceRunDTO findTestStepInstanceRunDTOById(long id) {
        l.info("with: " + id);
        TSInstance tsInstance = tsInstanceRepository.findOne(id);
        TSInstanceRunDTO tsInstanceRunDTO = mapper.map(tsInstance, TSInstanceRunDTO.class);
        l.info("found: " + tsInstanceRunDTO);
        return tsInstanceRunDTO;
    }

    public List<TSInstance> findAllTestStepInstances() {
        List<TSInstance> tsInstanceList;
        tsInstanceList = tsInstanceRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
        l.info("found: " + tsInstanceList);
        return tsInstanceList;
    }
}
