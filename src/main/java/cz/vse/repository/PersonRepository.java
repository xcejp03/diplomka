package cz.vse.repository;

import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.RoleEnum;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface PersonRepository extends BaseRepository<Person> {
    Person findByName(String username);

    List<Person> findAllPersonByProjectsMemberAndUserRoleOrderById(Project project, RoleEnum role);

    Person findByUsername(String username);

    @Query("select p from Person p where p.name = 'nic'")
    Person findXXX();


    @Query("select p from Person p join p.projectsMember pm where pm in (:projects)")
    List<Person> getProjectMembers(@Param("projects") Collection<Project> projects);

    @Query("select p from Person p join p.projectsMember pm join p.userRole ur where pm in (:projects) and ur.role = :role")
    List<Person> getProjectMembers(@Param("projects") Collection<Project> projects, @Param("role") RoleEnum role);
}
