package cz.vse.dao.impl;

import cz.vse.dao.TestCaseInstanceDao;
import cz.vse.entity.TestCaseInstance;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class TestCaseInstanceDaoImpl implements TestCaseInstanceDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @Override
    public void addTestCaseInstance(TestCaseInstance TestCaseInstance) {

    }

    @Override
    public void deleteTestCaseInstance(TestCaseInstance testCaseInstance) {

    }

    @Override
    public void updateTestCaseInstance(TestCaseInstance testCaseInstance) {

    }

    @Override
    public List<TestCaseInstance> getAllTestCaseInstance() {
        return null;
    }

    @Override
    public TestCaseInstance getTestCaseInstanceById(long id) {
        return null;
    }
}
