package cz.vse.service;

import cz.vse.dto.TCInstanceDTO;
import cz.vse.dto.TCInstanceList;
import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.entity.Project;
import cz.vse.entity.StatusEnum;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;

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

    List<TCInstance> findAllTCInstancesByTCMusterId(long id);

    List<TCInstanceDTO> findAllTCInstancesDTOByTCMusterId(long id);

    List<TCInstanceList> findAllTCInstanceListsByTCMusterId(long id);

    List<TCInstanceDTO> findTCInstancesDTOByWorkTCId(long id);

    List<TCInstanceList> findTCInstanceListsByWorkTCId(long id);

    TCInstanceRunDTO findTCInstanceRunDTOById(long id);

    TCInstance findLastTCInstanceByTCMuster(TCMuster tcMuster);

    TCInstance findLastTCInstanceByTCMusterId(long id);

    void refreshTCInstanceStatus(long id);

    int getNumberOfTCsInProjectByStatus(Project project, StatusEnum status);

}
