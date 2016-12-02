package cz.vse.service;

import cz.vse.dto.TSInstanceRunDTO;
import cz.vse.entity.TSInstance;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TSInstanceService {
    void createTestStepInstance(TSInstance tsInstance);

    void updateTestStepInstance(TSInstance tsInstance);

    void updateTestStepInstance(TSInstanceRunDTO tsInstanceRunDTO);

    void deleteTestStepInstance(TSInstance tsInstanceToDelete);

    void deleteTestStepInstanceById(long testStepInstanceToDeleteById);

    TSInstance findTestStepInstanceById(long id);

    TSInstanceRunDTO findTestStepInstanceRunDTOById(long id);

    List<TSInstance> findAllTestStepInstances();

//    List<TSInstance> findAllTSInstancesByTCInstanceId(Long id);

}
