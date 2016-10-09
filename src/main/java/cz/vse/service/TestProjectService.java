package cz.vse.service;

import cz.vse.dao.TestProjectDao;
import cz.vse.entity.TestProject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TestProjectService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    TestProjectDao testProjectDao;


    public void createTestProject(TestProject testProject) {
        l.debug("creating testProject - service");
        testProjectDao.saveTestProject(testProject);
        l.info("created testProject - service: " + testProject);
    }

    public void updateTestProject(TestProject testProject) {
        l.debug("updating testProject - service");
        testProjectDao.updateTestProject(testProject);
        l.info("updated testProject - service: " + testProject);
    }

    public void deleteTestProject(TestProject testProjectToDelete) {
        l.debug("deleting testProject - service");
        testProjectDao.deleteTestProject(testProjectToDelete);
        l.info("testProject deleted - service: " + testProjectToDelete);
    }

    public void deleteTestProjectById(long testProjectToDeleteById) {
        l.debug("deleting testProject - service");
        TestProject testProjectToDelete;
        testProjectToDelete = testProjectDao.getTestProjectById(testProjectToDeleteById);
        testProjectDao.deleteTestProject(testProjectToDelete);
        l.info("testProject deleted - service: " + testProjectToDelete);
    }

    public TestProject findTestProjectById(long id) {
        l.debug("finding testProject - service");
        TestProject testProject;
        testProject = testProjectDao.getTestProjectById(id);
        l.info("testProject founf - service: " + id + " - " + testProject);
        return testProject;
    }

    public List<TestProject> findAllTestProjects() {
        l.debug("finding all testProjects - service");
        List<TestProject> testProjectList;
        testProjectList = testProjectDao.getAllTestProjects();
        l.info("found all testProjects - service: " + testProjectList.toString());
        return testProjectList;
    }
}
