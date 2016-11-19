package cz.vse.dao;

import cz.vse.entity.TestSuite;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestSuiteDao {

    public void saveTestSuite(TestSuite testSuite);
    public void deleteTestSuite(Long testSuiteId);
    public void updateTestSuite(TestSuite testSuite);
    public List<TestSuite> getAll(TestSuite testSuite);
    public TestSuite getTestSuiteById(long id);
    public List<TestSuite> getAllTestSuites();
}
