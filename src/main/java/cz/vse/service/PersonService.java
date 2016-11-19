package cz.vse.service;

import cz.vse.dao.PersonDao;
import cz.vse.dto.PersonDTO;
import cz.vse.entity.Person;
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
public class PersonService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    PersonDao personDao;

    @Autowired
    private MapperFacade mapper;

    public void createPerson(PersonDTO personDTO) {
        l.debug("creating person - service");
        Person person = new Person();
        person = mapper.map(personDTO, Person.class);
        person.setCreatedDate(LocalDateTime.now());

        personDao.savePerson(person);
        l.info("created person - service: " + person);
    }

    public void updatePerson(PersonDTO personDTO) {
        l.debug("updating person - service");
        Person person = new Person();
        person = mapper.map(personDTO, Person.class);
        personDao.updatePerson(person);
        l.info("updated person - service: " + person);
    }

    public void deletePerson(Person personToDelete) {
        l.debug("deleting person - service");
        Long personId = personToDelete.getId();
        personDao.deletePerson(personId);
        l.info("person deleted - service: " + personToDelete);
    }

    public void deletePerson(long personToDeleteById) {
        l.debug("deleting person - service");
        personDao.deletePerson(personToDeleteById);
        l.info("person deleted - service: " + personToDeleteById);
    }

    public Person findPersonById(long id) {
        l.debug("finding person - service");
        Person person;
        person = personDao.getPersonById(id);
        l.info("person founf - service: " + id + " - " + person);
        return person;
    }

    public List<Person> findAllPersons() {
        l.debug("finding all persons - service");
        List<Person> personList;
        personList = personDao.getAllPersons();
        l.info("found all persons - service: " + personList.toString());
        return personList;
    }

}
