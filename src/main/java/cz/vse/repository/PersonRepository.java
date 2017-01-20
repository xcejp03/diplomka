package cz.vse.repository;

import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface PersonRepository extends BaseRepository<Person> {
    Person findById(long id);
    Person findByName(String username);
    List<Person> findAllPersonByProjectsMemberOrderById(Project project);

    Person findByUsername(String username);

    @Query("select p from Person p where p.name = 'nic'")
    Person findXXX();


    @Query("select p from Person p join p.projectsMember pm where p.projectsMember in :projects")
//    int getProjectMembersNumber(Project project);
    Person getProjectMembersNumber(@Param("projects") List<Project> projects);
}
