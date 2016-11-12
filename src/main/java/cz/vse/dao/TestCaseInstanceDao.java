package cz.vse.dao;

import cz.vse.entity.TCInstance;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestCaseInstanceDao {

    public void saveTestCaseInstance(TCInstance TCInstance);
    public void deleteTestCaseInstance(TCInstance TCInstance);
    public void updateTestCaseInstance(TCInstance TCInstance);
    public List<TCInstance> getAllTestCaseInstances();
    public TCInstance getTestCaseInstanceById(long id);
}
