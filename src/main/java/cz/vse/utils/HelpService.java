package cz.vse.utils;

/**
 * Created by pcejk on 29.12.2016.
 */

import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.repository.PersonRepository;
import cz.vse.repository.ProjectRepository;
import cz.vse.repository.UserRoleRepository;
import cz.vse.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HelpService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public void userRoleTesty()    {
        Person person = personRepository.findById(100);
        l.info(person);
        person.getUserRole().clear();
        personRepository.save(person);

        userRoleRepository.delete(person.getUserRole());

    }

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
