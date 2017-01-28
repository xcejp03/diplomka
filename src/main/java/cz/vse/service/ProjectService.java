package cz.vse.service;

import cz.vse.dto.PersonDTO;
import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectStatsDTO;
import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.RoleEnum;
import cz.vse.entity.TCStatusEnum;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface ProjectService {

    void createTestProject(ProjectDTO projectDTO);

    void createTestProject(Project project);

     void updateTestProject(ProjectDTO projectDTO);

     void deleteTestProject(Project projectToDelete);

     void deleteTestProjectById(long testProjectToDeleteById);

    Project findTestProjectById(long id);

    ProjectDTO findTestProjectDTOById(long id);

    List<ProjectDTO> findAllTestProjectsDTO();

    List<Project> findAllTestProjects();

    List<ProjectsNamesDTO> findAllTestProjectsByUserIdDTO(long id);

    List<ProjectStatsDTO> getMyProjectsWithStatistics(Person loggedPerson);

    int getNumberOfTCsInProject(long id);

    int getNumberOfTCsInProject(Project project);

    int getProjectMembersNumber(Project project);

    int getProjectMembersNumber(long projectId);

}
