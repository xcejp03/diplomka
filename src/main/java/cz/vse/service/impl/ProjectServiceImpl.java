package cz.vse.service.impl;

import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.TestSuite;
import cz.vse.repository.ProjectRepository;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
import cz.vse.utils.HelpService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreRemove;
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
    private ProjectRepository projectRepository;
    @Autowired
    private MapperFacade mapper;

    @Autowired
    private PersonService personService;

    @Autowired
    private SuiteService suiteService;

    @Autowired
    private HelpService hs;

    public void createTestProject(ProjectDTO projectDTO) {
        l.debug("creating project - service");
        Project project;
        project = mapper.map(projectDTO, Project.class);
        l.info("po mapování");
        List<Person> personMembersList = new ArrayList<>();
        personMembersList = project.getPersonMembers();
        List<Person> savedPersonList = personMembersList;

        Long projectSavedId = hs.saveProject(project);
        Project projectSav = projectRepository.findById(projectSavedId);
        refreshPersons(projectSav.getId(), savedPersonList);

        l.info("created project - service: " + project);
    }

    private void refreshPersons(Long projectId, List<Person> personMembersList) {
        Project project = projectRepository.findById(projectId);
        List<Project> projectList = new ArrayList<>();
        projectList.add(project);

        List<Person> refreshedPersonMemberList = new ArrayList<>();
        project.setPersonMembers(refreshedPersonMemberList);
        for (Person person : personMembersList) {
            l.info("person - " + person);
            person.setProjectsMember(projectList);
            refreshedPersonMemberList.add(person);
            l.info("vypisuju projectList - " + projectList);
        }
    }

    public void createTestProject(Project project) {
        l.debug("creating project - service");
        projectRepository.save(project);
        l.info("created project - service: " + project);
    }

    public void updateTestProject(ProjectDTO projectDTO) {
        l.debug("updating project - service");
        Project project = projectRepository.findOne(projectDTO.getId());
        mapper.map(projectDTO, project);
        List<Person> personList;
        personList = project.getPersonMembers();

//        clearProjectsPersonMembers(project);
        List<Person> personMembersList = new ArrayList<>();
        if (project.getPersonMembers() != null) {
            for (Person personForId : project.getPersonMembers()) {
                Person person = personService.findPersonById(personForId.getId());
                person.getProjectsMember().clear();
//                person.addTestProjectMember(project);
                personMembersList.add(person);
            }
            project.setPersonMembers(personMembersList);
        }
        if (project.getTestSuites() != null) {
            for (TestSuite suiteForId : project.getTestSuites()) {
                suiteForId.setProject(project);
            }

        }
        projectRepository.save(project);
        l.info("updated project - service: " + project);
    }

    private void clearProjectsPersonMembers(Project project) {
        List<Person> personList = project.getPersonMembers();
        List<Person> personEmptyList = new ArrayList<>();

        for (Person person : personList) {
            person.getProjectsMember().remove(project);
//            person.removeTestProjectMember(project);
//            personService.updatePerson(person);
            project.getPersonMembers().remove(person);
        }
        project.setPersonMembers(personEmptyList);
        projectRepository.save(project);

    }


    public void deleteTestProject(Project projectToDelete) {
        l.debug("deleting project - service");
        projectRepository.delete(projectToDelete);
        l.info("project deleted - service: " + projectToDelete);
    }

    public void deleteTestProjectById(long testProjectToDeleteById) {
        l.debug("deleting project - service");
        projectRepository.delete(testProjectToDeleteById);
        l.info("project deleted - service: " + testProjectToDeleteById);
    }

    public Project findTestProjectById(long id) {
        l.debug("finding project - service");
        Project project;
        project = projectRepository.findOne(id);
        l.info("project found - service: " + id + " - " + project);
        return project;
    }

    public ProjectDTO findTestProjectDTOById(long id) {
        l.debug("finding project - service");
        Project project;
        ProjectDTO projectDTO;
        project = projectRepository.findOne(id);
        projectDTO = mapper.map(project, ProjectDTO.class);
        l.info("projectDTO found - service: " + id + " - " + projectDTO);
        return projectDTO;
    }

    public List<ProjectDTO> findAllTestProjectsDTO() {
        l.debug("finding all testProjects - service");
        List<Project> projectList;
        List<ProjectDTO> projectDTOList;
        projectList = projectRepository.findAll();
        l.warn("mezkrok");
        projectDTOList = mapper.mapAsList(projectList, ProjectDTO.class);

        l.info("found all testProjects - service: ");
        return projectDTOList;
    }

    public List<Project> findAllTestProjects() {
        l.debug("finding all testProjects - service");
        List<Project> projectList;
        projectList = projectRepository.findAll();
        l.info("found all testProjects - service: ");
        return projectList;
    }

    public List<ProjectsNamesDTO> findAllTestProjectsByUserIdDTO(long id) {
        List<ProjectsNamesDTO> projectsNamesDTOList;
        List<Project> projectList;
        projectList = projectRepository.findAllProjectsByPersonMembersId(id);
        projectsNamesDTOList = mapper.mapAsList(projectList, ProjectsNamesDTO.class);
        return projectsNamesDTOList;
    }

//    public List<ProjectsNamesDTO> findAllTestProjectsByMemberIdDTO(long id) {
//        List<Project> projectList;
//        List<ProjectDTO> projectDTOList;
//        projectList = projectRepository.findAllProjectsByPersonMembersId(id);
//        projectDTOList = mapper.mapAsList(projectList, ProjectDTO.class);
//
//        l.info("domapovano");
//        return projectDTOList;
//    }

}
