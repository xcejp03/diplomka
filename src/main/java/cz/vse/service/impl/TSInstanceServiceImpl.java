package cz.vse.service.impl;

import cz.vse.dto.TSInstanceRunDTO;
import cz.vse.entity.StatusEnum;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TSInstance;
import cz.vse.repository.TCInstanceRepository;
import cz.vse.repository.TSInstanceRepository;
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
    private MapperFacade mapper;

    public void createTestStepInstance(TSInstance tsInstance) {
        l.debug("creating TSInstance - service");
        tsInstance.setCreatedDateTime(LocalDateTime.now());
        tsInstance.setStatus(StatusEnum.NORUN);
        tsInstanceRepository.save(tsInstance);
        l.info("created TSInstance - service: " + tsInstance);
    }

    public void updateTestStepInstance(TSInstance tsInstance) {
        l.debug("updating TSInstance - service");
        tsInstance.setUpdatedDateTime(LocalDateTime.now());
        tsInstanceRepository.save(tsInstance);
        l.info("updated TSInstance - service: " + tsInstance);
    }

    public void updateTestStepInstance(TSInstanceRunDTO tsInstanceRunDTO) {
        l.debug("updating TSInstance - service");
        TSInstance tsInstance = findTestStepInstanceById(tsInstanceRunDTO.getId());

        mapper.map(tsInstanceRunDTO, tsInstance);
        tsInstance.setUpdatedDateTime(LocalDateTime.now());
        l.info(tsInstance);
        tsInstanceRepository.save(tsInstance);
        l.info("updated TSInstance - service: " + tsInstance);
    }

    public void deleteTestStepInstance(TSInstance tsInstanceToDelete) {
        l.debug("deleting TSInstance - service");
        tsInstanceRepository.save(tsInstanceToDelete);
        l.info("TSInstance deleted - service: " + tsInstanceToDelete);
    }

    public void deleteTestStepInstanceById(long testStepInstanceToDeleteById) {
        l.debug("deleting TSInstance - service");
        TSInstance TSInstanceToDelete;
        tsInstanceRepository.delete(testStepInstanceToDeleteById);
        l.info("TSInstance deleted - service: " + testStepInstanceToDeleteById);
    }

    public TSInstance findTestStepInstanceById(long id) {
        l.debug("finding TSInstance - service");
        TSInstance tsInstance;
        tsInstance = tsInstanceRepository.findOne(id);
        l.info("TSInstance founf - service: " + id + " - " + tsInstance);
        return tsInstance;
    }

    public List<TSInstance> findAllTSInstancesByTCInstanceId(Long id) {
        l.info("finding TSInstances by TC id - "+id);
        List<TSInstance> tsInstanceList;

        TCInstance tcInstance = tcInstanceRepository.findOne(id);
        tsInstanceList = tsInstanceRepository.findAllTestStepInstancesByTCInstanceOrderById(tcInstance);
        return tsInstanceList;
    }


    public TSInstanceRunDTO findTestStepInstanceRunDTOById(long id) {
        l.debug("finding TSInstance - service");
        TSInstance tsInstance = tsInstanceRepository.findOne(id);
        TSInstanceRunDTO tsInstanceRunDTO = mapper.map(tsInstance, TSInstanceRunDTO.class);
        l.info("TSInstance founf - service: " + id + " - " + tsInstanceRunDTO);
        return tsInstanceRunDTO;
    }

    public List<TSInstance> findAllTestStepInstances() {
        l.debug("finding all testStepInstances - service");
        List<TSInstance> tsInstanceList;
        tsInstanceList = tsInstanceRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
        l.info("found all testStepInstances - service: " + tsInstanceList.toString());
        return tsInstanceList;
    }
}
