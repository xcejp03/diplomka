package cz.vse.dao;

import cz.vse.entity.TestCaseMuster;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestCaseMusterDao {

    public void addTestCaseMuster(TestCaseMuster testCaseMuster);
    public void deleteTestCaseMuster(TestCaseMuster testCaseMuster);
    public void updateTestCaseMuster(TestCaseMuster testCaseMuster);
    public List<TestCaseMuster> getAllTestCaseMuster();
    public TestCaseMuster getTestCaseMusterById(long id);
}
