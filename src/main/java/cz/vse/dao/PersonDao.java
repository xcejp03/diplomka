package cz.vse.dao;

import cz.vse.entity.Person;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface PersonDao {

    public void addPerson (Person person);       //vrací ID uložené osoby nebo -1 při chybě
    public void deletePerson (Person person);        //vrací true při úspěchu, false při chybě
    public void updatePerson (Person person);        //vrací true při úspěchu, false při chybě
    public List<Person> getAllPersons();
    public Person getPersonById(long id);
}
