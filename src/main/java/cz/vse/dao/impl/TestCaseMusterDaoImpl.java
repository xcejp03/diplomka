package cz.vse.dao.impl;

import cz.vse.dao.TestCaseMusterDao;
import cz.vse.entity.TestCaseMuster;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class TestCaseMusterDaoImpl implements TestCaseMusterDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @Override
    public void addTestCaseMuster(TestCaseMuster testCaseMuster) {

    }

    @Override
    public void deleteTestCaseMuster(TestCaseMuster testCaseMuster) {

    }

    @Override
    public void updateTestCaseMuster(TestCaseMuster testCaseMuster) {

    }

    @Override
    public List<TestCaseMuster> getAllTestCaseMuster() {
        return null;
    }

    @Override
    public TestCaseMuster getTestCaseMusterById(long id) {
        return null;
    }
}
