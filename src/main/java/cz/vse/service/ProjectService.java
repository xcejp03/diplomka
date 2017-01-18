package cz.vse.service;

import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;

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

    List<Project> getMyProjectsWithStatistics(Person loggedPerson);
}
