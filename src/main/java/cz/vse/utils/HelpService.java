package cz.vse.utils;

/**
 * Created by pcejk on 29.12.2016.
 */

import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.repository.PersonRepository;
import cz.vse.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HelpService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PersonRepository personRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long saveProject(Project project) {
//        project.getPersonMembers().clear();
        List<Person> people = new ArrayList<>();
        for (Person person: project.getPersonMembers()) {

            people.add(personRepository.findOne(person.getId()));
        }
        Person owner = personRepository.findOne(project.getProjectOwner().getId());
        project.setPersonMembers(people);
        project.setProjectOwner(owner);
        Project savedProject = projectRepository.save(project);

        return savedProject.getId();
    }

}
