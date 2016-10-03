package cz.vse.service;

import cz.vse.dao.PersonDao;
import cz.vse.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class PersonService {
    @Autowired
    private PersonDao personDao;

    public long savePerson (Person person)  {
        if (person != null) {
            return personDao.savePerson(person);
        }else {
            throw  new IllegalArgumentException("Person cannot be saved.");
        }

    }

}
