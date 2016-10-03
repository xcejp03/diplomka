package cz.vse.dao;

import cz.vse.entity.TestStepInstance;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestStepInstanceDao {

    public void saveTestStepInstance(TestStepInstance testStepInstance);
    public void deleteTestStepInstance(TestStepInstance testStepInstance);
    public void updateTestStepInstance(TestStepInstance testStepInstance);
    public List<TestStepInstance> getAllTestStepInstance();
    public TestStepInstance getTestStepInstanceById(long id);
}
