package cz.vse.service;

import cz.vse.dao.TestProjectDao;
import cz.vse.dto.TestProjectDTO;
import cz.vse.entity.TestProject;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
@Transactional
public class TestProjectService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    TestProjectDao testProjectDao;

    @Autowired
    private MapperFacade mapper;


    public void createTestProject(TestProjectDTO testProjectDTO) {
        l.debug("creating testProject - service");
        TestProject testProject = new TestProject();
        testProject =  mapper.map(testProjectDTO, TestProject.class);

        testProjectDao.saveTestProject(testProject);
        l.info("created testProject - service: " + testProject);
    }

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

    public List<TestProjectDTO> findAllTestProjects() {
        l.debug("finding all testProjects - service");
        List<TestProject> testProjectList = new ArrayList<>();
        List<TestProjectDTO> testProjectDTOList;
        testProjectList = testProjectDao.getAllTestProjects();
        l.warn("mezkrok");
        testProjectDTOList =  mapper.mapAsList(testProjectList, TestProjectDTO.class);

        l.info("found all testProjects - service: " + testProjectDTOList.toString());
        return testProjectDTOList;
    }
}
