package cz.vse.dao;

import cz.vse.entity.Project;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestProjectDao {

    public void saveTestProject(Project project);
    public void deleteTestProject(Project project);
    public void updateTestProject(Project project);
    public List<Project> getAllTestProjects();
    public Project getTestProjectById(long id);
}
