package cz.vse.dao;

import cz.vse.entity.TestSuite;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestSuiteDao {

    public void addTestSuite(TestSuite testSuite);
    public void deleteTestSuite(TestSuite testSuite);
    public void updateTestSuite(TestSuite testSuite);
    public List<TestSuite> getAll(TestSuite testSuite);
    public TestSuite getTestSuiteById(long id);
}
