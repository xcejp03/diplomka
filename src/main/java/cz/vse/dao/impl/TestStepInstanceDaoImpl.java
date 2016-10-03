package cz.vse.dao.impl;

import cz.vse.dao.TestStepInstanceDao;
import cz.vse.entity.TestStepInstance;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class TestStepInstanceDaoImpl implements TestStepInstanceDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @Override
    public void addTestStepInstance(TestStepInstance testStepInstance) {

    }

    @Override
    public void deleteTestStepInstance(TestStepInstance testStepInstance) {

    }

    @Override
    public void updateTestStepInstance(TestStepInstance testStepInstance) {

    }

    @Override
    public List<TestStepInstance> getAllTestStepInstance() {
        return null;
    }

    @Override
    public TestStepInstance getTestStepInstanceById(long id) {
        return null;
    }
}
