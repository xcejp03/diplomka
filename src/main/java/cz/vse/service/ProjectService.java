package cz.vse.service;

import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectForm;
import cz.vse.dto.ProjectName;
import cz.vse.dto.ProjectStatsDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface ProjectService {

    void createTestProject(ProjectDTO projectDTO);

    void createTestProject(Project project);

    void createTestProject(ProjectForm projectForm);

    void updateTestProject(ProjectDTO projectDTO);

    void updateTestProject(ProjectForm projectForm);

    void deleteTestProject(Project projectToDelete);

    void deleteTestProjectById(long testProjectToDeleteById);

    Project findTestProjectById(long id);

    ProjectDTO findTestProjectDTOById(long id);

    ProjectForm findTestProjectFormById(long id);

    ProjectName findTestProjectNameById(long id);

    List<ProjectDTO> findAllTestProjectsDTO();

    List<Project> findAllTestProjects();


    List<ProjectName> findAllTestProjectNamesByUserId(long id);

    List<Project> findAllTestProjectByUserId(long id);

    List<ProjectStatsDTO> getMyProjectsWithStatistics(Person loggedPerson);

    ProjectStatsDTO getProjectWithStatistics(Long projectId);

    int getNumberOfTCsInProject(long id);

    int getNumberOfTCsInProject(Project project);

    int getProjectMembersNumber(Project project);

    int getProjectMembersNumber(long projectId);

    ProjectDTO findProjectBySuiteId(long id);
}
