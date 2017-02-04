package cz.vse.service.impl;

import cz.vse.dto.PersonForm;
import cz.vse.dto.PersonName;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.RoleEnum;
import cz.vse.entity.UserRole;
import cz.vse.repository.PersonRepository;
import cz.vse.repository.ProjectRepository;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service("userDetailsService")
public class PersonServiceImpl implements PersonService, UserDetailsService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    ProjectRepository projectRepository;

    public void createPerson(PersonForm personForm) {
        l.info("with: " + personForm);
        Person person;
        person = mapper.map(personForm, Person.class);
        person.setCreatedDateTime(LocalDateTime.now());
        person.setPassword(hashPasswordForUser(personForm.getPassword()));
        person.setEnabled(true);
        personRepository.save(person);
        l.info("created: " + person);
    }

    public void updatePerson(PersonForm personForm) {
        l.info("with: " + personForm);
        Person person = personRepository.findOne(personForm.getId());
        mapper.map(personForm, person);
        personRepository.save(person);
        l.info("updated: " + person);
    }

    public void updatePerson(Person person) {
        l.info("with: " + person);
        personRepository.save(person);
    }

    public void updatePerson(List<Person> personList) {
        l.info("with: " + personList);
        personRepository.save(personList);
    }

    public void deletePerson(Person personToDelete) {
        l.info("with: " + personToDelete);
        personRepository.delete(personToDelete);
        l.info("deleted: " + personToDelete);
    }

    public void deletePerson(long personToDeleteById) {
        l.info("with: " + personToDeleteById);
        personRepository.delete(personToDeleteById);
    }

    public Person findPersonById(long id) {
        l.info("with: " + id);
        Person person;
        person = personRepository.findOne(id);
        l.info("found: " + person);
        return person;
    }

    @Override
    public PersonForm findPersonFormById(long id) {
        l.info("with: " + id);
        PersonForm personForm;
        Person person = personRepository.findOne(id);
        personForm = mapper.map(person, PersonForm.class);
        l.info("found: " + person);
        return personForm;
    }

    public List<Person> findAllPersons() {
        List<Person> personList;
        personList = personRepository.findAll();
        l.info("found: " + personList.toString());
        return personList;
    }

    public List<PersonForm> findAllPersonForms() {
        List<Person> personList;
        List<PersonForm> personForms;
        personList = personRepository.findAll();
        personForms = mapper.mapAsList(personList, PersonForm.class);
        personForms.sort(Comparator.comparing(PersonForm::getId));
        l.info("found: " + personList.toString());
        return personForms;
    }

    @Override
    public List<PersonName> findAllPersonNames() {
        List<Person> people = personRepository.findAll();
        List<PersonName> personNames = mapper.mapAsList(people, PersonName.class);
        l.info("found: " + personNames);
        return personNames;
    }

    @Override
    public List<Person> findAllPersonByProjectOrderById(Project project) {
        l.info("with: " + project);
        List<Person> personList;
        personList = personRepository.getProjectMembers(Arrays.asList(project));   //asi blbě, předělat
        l.fatal("NENÍ IMPLEMENTOVÁNO - NEFUNGUJE");
        l.info("found: " + personList);
        return personList;
    }

    @Override
    @Deprecated
    public List<PersonForm> findAllPersonFormsByProjectId(long id) {
        l.info("with: " + id);
        Project project;
        List<Person> personList;
        List<PersonForm> personForms;
        project = projectService.findTestProjectById(id);
        personList = findAllPersonByProjectOrderById(project);
        personForms = mapper.mapAsList(personList, PersonForm.class);
        l.info("found: " + personForms);
        return personForms;
    }

    @Override
    public List<Person> findAllPersonByProjectIdOrderById(long id) {
        l.info("with: " + id);
        Project project;
        List<Person> personList;
        project = projectService.findTestProjectById(id);
        personList = findAllPersonByProjectOrderById(project);
        l.info("found: " + personList);
        return personList;
    }

    @Override
    public List<PersonForm> findAllPersonFormsByProjectIdOrderById(long id) {
        l.info("with: " + id);
        Project project;
        List<Person> people;
        List<PersonForm> personForms;
        project = projectService.findTestProjectById(id);
        people = findAllPersonByProjectOrderById(project);
        personForms = mapper.mapAsList(people, PersonForm.class);
        l.info("found: " + personForms);
        return personForms;
    }

    @Override
    public List<PersonName> findAllPersonNamesByProjectIdOrderById(long id) {
        l.info("with: " + id);
        Project project;
        List<Person> people;
        List<PersonName> personNames;
        project = projectService.findTestProjectById(id);
        people = findAllPersonByProjectOrderById(project);
        personNames = mapper.mapAsList(people, PersonName.class);
        l.info("found: " + personNames);
        return personNames;
    }

    @Override
    public Person findPersonByLogin(String login) {
        l.info("with: " + login);
        return personRepository.findByUsername(login);
    }

    @Override
    public Person findPersonByName(String name) {
        l.info("with: " + name);
        return personRepository.findByName(name);
    }


    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        l.info("with: " + username);
        Person user = personRepository.findByUsername(username);
        l.info("uživatel: " + user);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
        l.info("role uživatele:" + authorities);
        return buildUserForAuthentication(user, authorities);

    }

    private User buildUserForAuthentication(Person user,
                                            List<GrantedAuthority> authorities) {
        l.info("build new user with: " + user + "and" + authorities);
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        l.info(" builUserAuthority");
        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole().getRoleString()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
        l.info("Granted autority: " + Result);
        return Result;
    }

    public Person findPersonByAuthentication(Authentication auth) {
        l.info("with: " + auth);
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Person person = findPersonByLogin(userDetail.getUsername());
        l.info("found: " + person);
        return person;
    }

    private String hashPasswordForUser(String passwordUnhashed) {
        String passwordHashed;
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        passwordHashed = encoder.encode(passwordUnhashed);
        return passwordHashed;
    }

    @Override
    public List<PersonName> getProjectMembers(long id) {
        l.info("with: " + id);
        Project project = projectService.findTestProjectById(id);
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        List<Person> people = personRepository.getProjectMembers(projects);
        List<PersonName> personNames;
        personNames = mapper.mapAsList(people, PersonName.class);
        l.info("getted: " + personNames);
        return personNames;
    }

    @Override
    public List<PersonName> getProjectMembers(long projectId, RoleEnum roleEnum) {
        l.info("with: " + projectId + " and " + roleEnum);
        Project project = projectService.findTestProjectById(projectId);
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        List<Person> people = personRepository.getProjectMembers(projects, roleEnum);
        List<PersonName> personNames;
        personNames = mapper.mapAsList(people, PersonName.class);
        l.info("found: " + personNames);
        return personNames;
    }
}
