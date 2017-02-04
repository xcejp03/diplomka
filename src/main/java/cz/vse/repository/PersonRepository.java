package cz.vse.repository;

import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.RoleEnum;
import cz.vse.entity.UserRole;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface PersonRepository extends BaseRepository<Person> {
    Person findByName(String username);

    List<Person> findAllPersonByProjectsMemberAndUserRoleOrderById(Project project, RoleEnum role);

    //    @Query("select * from Project pr join pr.personMembers pe");
//    List<Person> getOsoby('ddd');
//    projectsMember
    Person findByUsername(String username);

    @Query("select p from Person p where p.name = 'nic'")
    Person findXXX();

//    List<Person> findByProjectsMember_name(String name); taky funguje


    @Query("select p from Person p join p.projectsMember pm where pm in (:projects)")
    List<Person> getProjectMembers(@Param("projects") Collection<Project> projects);

//    @Query("select p from Person p join p.projectsMember pm where pm in (:projects) and p.userRole in (:roles)")
    @Query("select p from Person p join p.projectsMember pm join p.userRole ur where pm in (:projects) and ur.role = :role")
    List<Person> getProjectMembers(@Param("projects") Collection<Project> projects, @Param("role") RoleEnum role);
//    List<Person> getProjectMembers(@Param("projects") Collection<Project> projects);
}
