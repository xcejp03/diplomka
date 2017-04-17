package cz.vse.repository;

import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.TCMuster;
import cz.vse.entity.TestSuite;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface TCMusterRepository extends BaseRepository<TCMuster> {
    List<TCMuster> findAllTCMustersByTestSuitesOrderById(TestSuite testSuite);

    List<TCMuster> findAllTCByProjectIn(List<Project> projectList);


    @Query("select count(tc.id) from TCMuster tc where tc.Author = :loggedPerson and tc.project = :project")
    int getNumberOfMyTCsInProject(@Param("loggedPerson") Person loggedPerson, @Param("project") Project project);

    @Query("select count(tc.id) from TCMuster tc where tc.project = :project")
    int getNumberOfTCsInProject(@Param("project") Project project);
}
