package cz.vse.service;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.Person;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface PersonService {
    void createPerson(PersonDTO personDTO);

    void updatePerson(PersonDTO personDTO);

    void deletePerson(Person personToDelete);

    void deletePerson(long personToDeleteById);

    Person findPersonById(long id);

    List<Person> findAllPersons();

    List<PersonDTO> findAllPersonsDTO();

}
