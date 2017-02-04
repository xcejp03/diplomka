package cz.vse.service;

import cz.vse.dto.PersonForm;
import cz.vse.dto.PersonName;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.RoleEnum;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
//@Service("userDetailsService")
public interface PersonService {
    void createPerson(PersonForm personForm);

    void updatePerson(PersonForm personForm);

    void updatePerson(Person person);

    void updatePerson(List<Person> personList);

    void deletePerson(Person personToDelete);

    void deletePerson(long personToDeleteById);

    Person findPersonById(long id);

    PersonForm findPersonFormById(long id);

    List<Person> findAllPersons();

    List<PersonForm> findAllPersonForms();

    List<PersonName> findAllPersonNames ();

    List<Person> findAllPersonByProjectOrderById(Project project);

    List<Person> findAllPersonByProjectIdOrderById(long id);

    List<PersonForm> findAllPersonFormsByProjectIdOrderById(long id);

    List<PersonName> findAllPersonNamesByProjectIdOrderById(long id);

    List<PersonForm> findAllPersonFormsByProjectId (long id);

    Person findPersonByLogin(String login);

    Person findPersonByName(String name);

    Person findPersonByAuthentication(Authentication auth);

    List<PersonName> getProjectMembers(long projectId);

    List<PersonName> getProjectMembers(long projectId, RoleEnum roleEnum);

//    List<PersonDTO> getProjectMembersByProjectIdAndRole(long id, UserRole role);

}
