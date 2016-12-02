package cz.vse.service;

import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.entity.TCInstance;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TCInstanceService {

    void createTestCaseInstance(TCInstance tcInstance);

    void updateTestCaseInstance(TCInstance tcInstance);

    void deleteTestCaseInstance(TCInstance tcInstanceToDelete);

    void deleteTestCaseInstanceById(long testCaseInstanceToDeleteById);

    TCInstance findTestCaseInstanceById(long id);

    List<TCInstance> findAllTestCaseInstances();

//    List<TCInstance> findAllTCInstancesByTCMusterId(long id);

    TCInstanceRunDTO findTCInstanceRunDTOById(long id);

}
