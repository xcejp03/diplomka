package cz.vse.service.impl;

import cz.vse.dao.TestProjectDao;
import cz.vse.dto.ProjectDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    TestProjectDao testProjectDao;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private PersonService personService;

    public void createTestProject(ProjectDTO projectDTO) {
        l.debug("creating project - service");
        Project project = new Project();
        project = mapper.map(projectDTO, Project.class);

        /**
         * entita musí být nastavena z obou směrů - předělat
         */
        List<Person> personMembersList = new ArrayList<>();
        if (project.getPersonMembers() != null) {
            for (Person personForId : project.getPersonMembers()) {
                Person person = personService.findPersonById(personForId.getId());
                person.addTestProjectMember(project);
                personMembersList.add(person);
            }
            project.setPersonMembers(personMembersList);
        }
        testProjectDao.saveTestProject(project);
        l.info("created project - service: " + project);
    }

    public void createTestProject(Project project) {
        l.debug("creating project - service");
        testProjectDao.saveTestProject(project);
        l.info("created project - service: " + project);
    }

    public void updateTestProject(ProjectDTO projectDTO) {
        l.debug("updating project - service");
        Project project = new Project();
        project = mapper.map(projectDTO, Project.class);

        List<Person> personMembersList = new ArrayList<>();
        if (project.getPersonMembers() != null) {
            for (Person personForId : project.getPersonMembers()) {
                Person person = personService.findPersonById(personForId.getId());
                person.addTestProjectMember(project);
                personMembersList.add(person);
            }
            project.setPersonMembers(personMembersList);
        }
        testProjectDao.updateTestProject(project);
        l.info("updated project - service: " + project);
    }

    public void deleteTestProject(Project projectToDelete) {
        l.debug("deleting project - service");
        Long projectId = projectToDelete.getId();
        testProjectDao.deleteTestProject(projectId);
        l.info("project deleted - service: " + projectToDelete);
    }

    public void deleteTestProjectById(long testProjectToDeleteById) {
        l.debug("deleting project - service");
        testProjectDao.deleteTestProject(testProjectToDeleteById);
        l.info("project deleted - service: " + testProjectToDeleteById);
    }

    public Project findTestProjectById(long id) {
        l.debug("finding project - service");
        Project project;
        project = testProjectDao.getTestProjectById(id);
        l.info("project found - service: " + id + " - " + project);
        return project;
    }

    public ProjectDTO findTestProjectDTOById(long id) {
        l.debug("finding project - service");
        Project project;
        project = testProjectDao.getTestProjectById(id);
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO = mapper.map(project, ProjectDTO.class);
        l.info("projectDTO found - service: " + id + " - " + projectDTO);
        return projectDTO;
    }

    public List<ProjectDTO> findAllTestProjectsDTO() {
        l.debug("finding all testProjects - service");
        List<Project> projectList = new ArrayList<>();
        List<ProjectDTO> projectDTOList;
        projectList = testProjectDao.getAllTestProjects();
        l.warn("mezkrok");
        projectDTOList = mapper.mapAsList(projectList, ProjectDTO.class);

        l.info("found all testProjects - service: ");
        return projectDTOList;
    }

    public List<Project> findAllTestProjects() {
        l.debug("finding all testProjects - service");
        List<Project> projectList = new ArrayList<>();
        projectList = testProjectDao.getAllTestProjects();
        l.info("found all testProjects - service: ");
        return projectList;
    }
}
