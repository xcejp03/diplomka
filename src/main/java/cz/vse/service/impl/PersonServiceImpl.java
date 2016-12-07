package cz.vse.service.impl;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.Person;
import cz.vse.repository.PersonRepository;
import cz.vse.service.PersonService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class PersonServiceImpl implements PersonService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private PersonRepository personRepository;

    public void createPerson(PersonDTO personDTO) {
        l.debug("creating person - service");
        Person person;
        person = mapper.map(personDTO, Person.class);
        person.setCreatedDate(LocalDateTime.now());
        personRepository.save(person);
        l.info("created person - service: " + person);
    }

    public void updatePerson(PersonDTO personDTO) {
        l.debug("updating person - service");
        Person person = personRepository.findOne(personDTO.getId());
//        person = mapper.map(personDTO, Person.class);
        mapper.map(personDTO, person);
        person.setLastLogin(LocalDateTime.now());
        personRepository.save(person);
        l.info("updated person - service: " + person);
    }

    public void deletePerson(Person personToDelete) {
        l.debug("deleting person - service");
        personRepository.delete(personToDelete);
        l.info("person deleted - service: " + personToDelete);
    }

    public void deletePerson(long personToDeleteById) {
        l.debug("deleting person - service");
        personRepository.delete(personToDeleteById);
        l.info("person deleted - service: " + personToDeleteById);
    }

    public Person findPersonById(long id) {
        l.debug("finding person - service");
        Person person;
        person = personRepository.findOne(id);
        l.info("person founf - service: " + id + " - " + person);
        return person;
    }

    public List<Person> findAllPersons() {
        l.debug("finding all persons - service");
        List<Person> personList;
        personList = personRepository.findAll();
        l.info("found all persons - service: " + personList.toString());
        return personList;
    }

}
