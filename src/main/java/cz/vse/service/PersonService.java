package cz.vse.service;

import cz.vse.dao.PersonDao;
import cz.vse.entity.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class PersonService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    PersonDao personDao;
//    @Autowired
//    private PersonDao personDao;
//
//    public long savePerson (Person person)  {
//        if (person != null) {
//            return personDao.savePerson(person);
//        }else {
//            throw  new IllegalArgumentException("Person cannot be saved.");
//        }
//
//    }

    public void createPerson(Person person) {
        l.debug("creating person - service");
        personDao.savePerson(person);
        l.info("created person - service: " + person);
    }

    public void updatePerson(Person person) {
        l.debug("updating person - service");
        personDao.updatePerson(person);
        l.info("updated person - service: " + person);
    }

    public void deletePerson(Person personToDelete) {
        l.debug("deleting person - service");
        personDao.deletePerson(personToDelete);
        l.info("person deleted - service: " + personToDelete);
    }

    public void deletePersonById(long personToDeleteById) {
        l.debug("deleting person - service");
        Person personToDelete;
        personToDelete = personDao.getPersonById(personToDeleteById);
        personDao.deletePerson(personToDelete);
        l.info("person deleted - service: " + personToDelete);
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
