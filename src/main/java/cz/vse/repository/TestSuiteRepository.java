package cz.vse.repository;

import cz.vse.entity.Project;
import cz.vse.entity.TestSuite;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface TestSuiteRepository extends BaseRepository<TestSuite> {
    List<TestSuite> findAllTestSuitesByProjectOrderById(Project project);

    List<TestSuite> findAllTestSuitesByProjectIn(List<Project> projectList);

    @Query("select count(ts.id) from TestSuite ts where ts.project = :project")
    int getNumberOfSuitesInProject(@Param("project") Project project);
}
