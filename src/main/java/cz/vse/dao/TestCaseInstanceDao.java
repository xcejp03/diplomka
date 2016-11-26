package cz.vse.dao;

import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestCaseInstanceDao {

    void saveTestCaseInstance(TCInstance TCInstance);
    void deleteTestCaseInstance(TCInstance TCInstance);
    void updateTestCaseInstance(TCInstance TCInstance);
    List<TCInstance> getAllTestCaseInstances();
    TCInstance getTestCaseInstanceById(long id);
    List<TCInstance> getAllTCInstancesByTCMuster(TCMuster tcMuster);
    List<TCInstance> getAllTCInstancesByTCMusterId(long id);
}
