package cz.vse.dao.impl;

import cz.vse.dao.TestSuiteDao;
import cz.vse.entity.TestSuite;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class TestSuiteDaoImpl implements TestSuiteDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @Override
    public void addTestSuite(TestSuite testSuite) {

    }

    @Override
    public void deleteTestSuite(TestSuite testSuite) {

    }

    @Override
    public void updateTestSuite(TestSuite testSuite) {

    }

    @Override
    public List<TestSuite> getAll(TestSuite testSuite) {
        return null;
    }

    @Override
    public TestSuite getTestSuiteById(long id) {
        return null;
    }
}
