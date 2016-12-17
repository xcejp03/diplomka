package cz.vse.repository;

import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.entity.Project;
import cz.vse.repository.base.BaseRepository;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface ProjectRepository extends BaseRepository<Project> {
    List<ProjectsNamesDTO> findAllProjectsByPersonMembersId(Long id);
}
