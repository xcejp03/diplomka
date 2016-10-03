package cz.vse.dao;

import cz.vse.entity.TestProject;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestProjectDao {

    public void addTestProject(TestProject testProject);
    public void deleteTestProject(TestProject testProject);
    public void updateTestProject(TestProject testProject);
    public List<TestProject> getAllTestProject();
    public TestProject getTestProjectById(long id);
}
