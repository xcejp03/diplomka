package cz.vse.repository;

import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.repository.base.BaseRepository;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface PersonRepository extends BaseRepository<Person> {
    Person findById(long id);
    Person findByName(String username);
    List<Person> findAllPersonByProjectsMember(Project project);

    Person findByUsername(String username);
}
