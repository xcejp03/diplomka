package cz.vse.service.impl;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.RoleEnum;
import cz.vse.entity.UserRole;
import cz.vse.repository.PersonRepository;
import cz.vse.repository.ProjectRepository;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.RoleService;
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

    public void createPerson(PersonDTO personDTO) {
        l.info("with: "+ personDTO);
        Person person;
        person = mapper.map(personDTO, Person.class);
        person.setCreatedDateTime(LocalDateTime.now());
        person.setPassword(hashPasswordForUser(personDTO.getPassword()));
        person.setEnabled(true);
        personRepository.save(person);
        l.info("created: " + person);
    }

    public void updatePerson(PersonDTO personDTO) {
        l.info("with: "+ personDTO);
        Person person = personRepository.findOne(personDTO.getId());
        mapper.map(personDTO, person);
        person.setLastLogged(LocalDateTime.now());
        personRepository.save(person);
        l.info("updated: " + person);
    }

    public void updatePerson(Person person) {
        l.info("with: "+ person);
        personRepository.save(person);
    }

    public void updatePerson(List<Person> personList) {
        l.info("with: "+ personList);
        personRepository.save(personList);
    }

    public void deletePerson(Person personToDelete) {
        l.info("with: "+ personToDelete);
        personRepository.delete(personToDelete);
        l.info("deleted: " + personToDelete);
    }

    public void deletePerson(long personToDeleteById) {
        l.info("with: "+ personToDeleteById);
        personRepository.delete(personToDeleteById);
    }

    public Person findPersonById(long id) {
        l.info("with: "+ id);
        Person person;
        person = personRepository.findOne(id);
        l.info("found: " + person);
        return person;
    }

    public List<Person> findAllPersons() {
        List<Person> personList;
        personList = personRepository.findAll();
        l.info("found: "+ personList.toString());
        return personList;
    }

    public List<PersonDTO> findAllPersonsDTO() {
        List<Person> personList;
        List<PersonDTO> personDTOList;
        personList = personRepository.findAll();
        personDTOList = mapper.mapAsList(personList, PersonDTO.class);
        l.info("found: " + personList.toString());
        return personDTOList;
    }

    public List<Person> findAllPersonByProjectOrderById(Project project) {
        l.info("with: "+ project);
        List<Person> personList;
        personList = personRepository.getProjectMembers(Arrays.asList(project));   //asi blbě, předělat
        l.fatal("NENÍ IMPLEMENTOVÁNO - NEFUNGUJE");
        l.info("found: "+ personList);
        return personList;
    }

    public List<PersonDTO> findAllPersonDTOByProjectId(long id) {
        l.info("with: "+ id);
        Project project;
        List<Person> personList;
        List<PersonDTO> personDTOList;
        project = projectService.findTestProjectById(id);
        personList = findAllPersonByProjectOrderById(project);
        personDTOList = mapper.mapAsList(personList, PersonDTO.class);
        l.info("found: "+ personDTOList);
        return personDTOList;
    }

    public List<Person> findAllPersonByProjectIdOrderById(long id) {
        l.info("with: "+ id);
        Project project;
        List<Person> personList;
        project = projectService.findTestProjectById(id);
        personList = findAllPersonByProjectOrderById(project);
        l.info("found: "+ personList);
        return personList;
    }


    public Person findPersonByLogin(String login) {
        l.info("with: "+ login);
        return personRepository.findByUsername(login);
    }

    public Person findPersonByName(String name) {
        l.info("with: "+ name);
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
        l.info("build new user with: "+user + "and" + authorities);
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
        l.info("with: "+ auth);
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Person person = findPersonByLogin(userDetail.getUsername());
        l.info("found: "+ person);
        return person;
    }

    private String hashPasswordForUser(String passwordUnhashed) {
        String passwordHashed;
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        passwordHashed = encoder.encode(passwordUnhashed);
        return passwordHashed;
    }

    @Override
    public List<PersonDTO> getProjectMembers(long id) {
        l.info("with: "+ id);
        Project project = projectService.findTestProjectById(id);
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        List<Person> people = personRepository.getProjectMembers(projects);
        List<PersonDTO> peopleDTO;
        peopleDTO = mapper.mapAsList(people, PersonDTO.class);
        l.info("getted: "+ peopleDTO);
        return peopleDTO;
    }

    @Override
    public List<PersonDTO> getProjectMembers(long projectId, RoleEnum roleEnum) {
        l.info("with: "+ projectId+ " and "+ roleEnum);
        Project project = projectService.findTestProjectById(projectId);
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        List<Person> people = personRepository.getProjectMembers(projects, roleEnum);
        List<PersonDTO> peopleDTO;
        peopleDTO = mapper.mapAsList(people, PersonDTO.class);
        l.info("found: "+ peopleDTO);
        return peopleDTO;
    }
}
