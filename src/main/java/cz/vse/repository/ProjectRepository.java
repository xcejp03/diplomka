package cz.vse.repository;

import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.TestSuite;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface ProjectRepository extends BaseRepository<Project> {
    Project findById(long id);
    Project findByTestSuitesIn(List<TestSuite> suites);

    List<Project> findAllProjectsByPersonMembersIdOrderById(Long id);


    List<Person> findAllPersonsByPersonMembers(List<Project> projects);
    List<Person> findAllPersonByPersonMembers(List<Project> projects);

    List<Person> findAllPersonsByPersonMembers(Project project);
    List<Person> findAllPersonByPersonMembers(Project project);


    @Query("select count (p.id) from Project p join p.tcMusters t where t.Author in :loggedPerson")
        //UKÁZKA JOIN
    int getNumberOfMyTCsInProject(@Param("loggedPerson") List<Person> loggedPerson);

    @Query("select count(p.id) from Project p join p.personMembers pm where p.id = :projectId")
    int getProjectMembersNumber(@Param("projectId") long projectId);



}