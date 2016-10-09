package cz.vse.dao;

import cz.vse.entity.TestCaseInstance;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestCaseInstanceDao {

    public void saveTestCaseInstance(TestCaseInstance TestCaseInstance);
    public void deleteTestCaseInstance(TestCaseInstance testCaseInstance);
    public void updateTestCaseInstance(TestCaseInstance testCaseInstance);
    public List<TestCaseInstance> getAllTestCaseInstances();
    public TestCaseInstance getTestCaseInstanceById(long id);
}
