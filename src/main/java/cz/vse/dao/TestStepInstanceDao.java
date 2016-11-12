package cz.vse.dao;

import cz.vse.entity.TSInstance;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestStepInstanceDao {

    public void saveTestStepInstance(TSInstance TSInstance);
    public void deleteTestStepInstance(TSInstance TSInstance);
    public void updateTestStepInstance(TSInstance TSInstance);
    public List<TSInstance> getAllTestStepInstances();
    public TSInstance getTestStepInstanceById(long id);
}
