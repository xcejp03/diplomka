package cz.vse.service.impl;

import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectForm;
import cz.vse.dto.ProjectName;
import cz.vse.dto.ProjectStatsDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
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
        l.debug("with: " + projectDTO);
        Project project = mapper.map(projectDTO, Project.class);
        project.setCreatedDateTime(LocalDateTime.now());
        projectRepository.save(project);
        l.info("created: " + project);
    }

    public void createTestProject(Project project) {
        l.debug("with: " + project);
        projectRepository.save(project);
        project.setCreatedDateTime(LocalDateTime.now());
        l.info("created: " + project);
    }

    @Override
    public void createTestProject(ProjectForm projectForm) {
        l.debug("with: " + projectForm);
        Project project = mapper.map(projectForm, Project.class);
        project.setCreatedDateTime(LocalDateTime.now());
        projectRepository.save(project);
        l.info("created: " + project);
    }

    public void updateTestProject(ProjectDTO projectDTO) {
        l.debug("with: " + projectDTO);
        Project project = projectRepository.findOne(projectDTO.getId());
        project.setUpdatedDateTime(LocalDateTime.now());
        mapper.map(projectDTO, project);
        projectRepository.save(project);
        l.info("updated: " + projectDTO);
    }

    @Override
    public void updateTestProject(ProjectForm projectForm) {
        l.debug("with: " + projectForm);
        Project project = projectRepository.findOne(projectForm.getId());
        project.setUpdatedDateTime(LocalDateTime.now());
        mapper.map(projectForm, project);
        projectRepository.save(project);
        l.info("updated: " + projectForm);
    }

    public void deleteTestProject(Project projectToDelete) {
        l.info("with: " + projectToDelete);
        projectRepository.delete(projectToDelete);
        l.info("deleted " + projectToDelete);
    }

    public void deleteTestProjectById(long testProjectToDeleteById) {
        l.info("with: " + testProjectToDeleteById);
        projectRepository.delete(testProjectToDeleteById);
        l.info("deleted: " + testProjectToDeleteById);
    }

    public Project findTestProjectById(long id) {
        l.info("with: " + id);
        Project project;
        project = projectRepository.findOne(id);
        l.info("found: " + project);
        return project;
    }

    public ProjectDTO findTestProjectDTOById(long id) {
        l.info("with: " + id);
        Project project;
        ProjectDTO projectDTO;
        project = projectRepository.findOne(id);
        projectDTO = mapper.map(project, ProjectDTO.class);
        l.info("found: " + projectDTO);
        return projectDTO;
    }

    @Override
    public ProjectForm findTestProjectFormById(long id) {
        l.info("with: " + id);
        Project project = projectRepository.findOne(id);
        ProjectForm projectForm = mapper.map(project, ProjectForm.class);
        l.info("found: " + projectForm);
        return projectForm;
    }

    @Override
    public ProjectName findTestProjectNameById(long id) {
        l.info("with: " + id);
        Project project = projectRepository.findOne(id);
        ProjectName projectName = mapper.map(project, ProjectName.class);
        l.info("found: " + projectName);
        return projectName;
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

    public List<ProjectName> findAllTestProjectNamesByUserId(long id) {
        List<ProjectName> projectNames;
        List<Project> projectList;
        projectList = projectRepository.findAllProjectsByPersonMembersIdOrderById(id);
        projectNames = mapper.mapAsList(projectList, ProjectName.class);
        return projectNames;
    }

    public List<Project> findAllTestProjectByUserId(long id) {
        List<Project> projects = projectRepository.findAllProjectsByPersonMembersIdOrderById(id);
        return projects;
    }


    @Override
    public List<ProjectStatsDTO> getMyProjectsWithStatistics(Person loggedPerson) {
        List<Project> projects = projectRepository.findAllProjectsByPersonMembersIdOrderById(loggedPerson.getId());
        List<ProjectStatsDTO> projectStatsDTOs;
        projectStatsDTOs = mapper.mapAsList(projects, ProjectStatsDTO.class);
        l.warn("projectStatsDTO: " + projectStatsDTOs);
        return projectStatsDTOs;
    }

    @Override
    public ProjectStatsDTO getProjectWithStatistics(Long projectId) {
        Project project = projectRepository.findOne(projectId);
        ProjectStatsDTO projectStatsDTO;
        projectStatsDTO = mapper.map(project, ProjectStatsDTO.class);
        l.warn("projectStatsDTO: " + projectStatsDTO);
        return projectStatsDTO;
    }

    @Override
    public int getNumberOfTCsInProject(long id) {
        Project project = findTestProjectById(id);
        return project.getTcMusters().size();
    }

    @Override
    public int getNumberOfTCsInProject(Project project) {
        return project.getTcMusters().size();
    }


    @Override
    public int getProjectMembersNumber(Project project) {
        return projectRepository.getProjectMembersNumber(project.getId());

    }

    @Override
    public int getProjectMembersNumber(long projectId) {
        return projectRepository.getProjectMembersNumber(projectId);
    }

    @Override
    public ProjectDTO findProjectBySuiteId(long id) {
        l.info("with: " + id);
        ProjectDTO projectDTO;
        TestSuite suite = suiteService.findTestSuiteById(id);
        Project project = projectRepository.findByTestSuitesIn(Arrays.asList(suite));
        projectDTO = mapper.map(project, ProjectDTO.class);
        l.info("found: " + projectDTO);
        return projectDTO;
    }
}
