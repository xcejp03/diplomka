package cz.vse.service.impl;

import cz.vse.dto.PersonDTO;
import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectStatsDTO;
import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.RoleEnum;
import cz.vse.entity.TCStatusEnum;
import cz.vse.repository.ProjectRepository;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
import cz.vse.utils.HelpService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
        projectRepository.save(project);

        l.info("created project - service: " + project);
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


        projectRepository.save(project);
        l.info("updated project - service: " + project);
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
        projectList = projectRepository.findAllProjectsByPersonMembersIdOrderById(id);
        projectsNamesDTOList = mapper.mapAsList(projectList, ProjectsNamesDTO.class);
        return projectsNamesDTOList;
    }


    @Override
    public List<ProjectStatsDTO> getMyProjectsWithStatistics(Person loggedPerson) {
        List<Project> projects = projectRepository.findAllProjectsByPersonMembersIdOrderById(loggedPerson.getId());
        List<ProjectStatsDTO> projectStatsDTOs;
        projectStatsDTOs = mapper.mapAsList(projects, ProjectStatsDTO.class);
        l.warn("projectStatsDTO: "+ projectStatsDTOs);
        return projectStatsDTOs;
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


//    @Override
//    public int getNumberOfTCsInProjectByStatus(Project project, TCStatusEnum status) {
//        return 0;
//    }
//select count
//groupByTCStatusEnum
//    findTop1ByTCMusterOrderByCreatedDateTimeDesc

//    @Override
//    public int getNumberOfTCsInProjectByStatus(long projectId, TCStatusEnum status) {
//        return 0;
//    }

    @Override
    public int getProjectMembersNumber(Project project) {
        return projectRepository.getProjectMembersNumber(project.getId());

    }

    @Override
    public int getProjectMembersNumber(long projectId) {
        return projectRepository.getProjectMembersNumber(projectId);
    }

}
