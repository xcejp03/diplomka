package cz.vse.service;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.Person;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
//@Service("userDetailsService")
public interface PersonService {
    void createPerson(PersonDTO personDTO);

    void updatePerson(PersonDTO personDTO);

    void updatePerson(Person person);

    void updatePerson(List<Person> personList);

    void deletePerson(Person personToDelete);

    void deletePerson(long personToDeleteById);

    Person findPersonById(long id);

    List<Person> findAllPersons();

    List<PersonDTO> findAllPersonsDTO();

    Person findPersonByLogin(String login);

    Person findPersonByName(String name);

    Person findPersonByAuthentication(Authentication auth);

}
