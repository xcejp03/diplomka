package cz.vse.dao;

import cz.vse.entity.TSInstance;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestStepInstanceDao {

    void saveTestStepInstance(TSInstance TSInstance);
    void deleteTestStepInstance(TSInstance TSInstance);
    void updateTestStepInstance(TSInstance TSInstance);
    List<TSInstance> getAllTestStepInstances();
    TSInstance getTestStepInstanceById(long id);
    List<TSInstance> getAllTestStepInstancesByTCInstanceId(long id);
}
