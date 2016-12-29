package cz.vse.service.impl;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.Person;
import cz.vse.entity.UserRole;
import cz.vse.repository.PersonRepository;
import cz.vse.service.PersonService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void createPerson(PersonDTO personDTO) {
        l.debug("creating person - service");
        Person person;
        person = mapper.map(personDTO, Person.class);
        person.setCreatedDate(LocalDateTime.now());
        person.setPassword(hashPasswordForUser(personDTO.getPassword()));
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

    public void updatePerson (Person person){
        personRepository.save(person);
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updatePerson (List<Person> personList){
        List<Person> personListLocal = personList;
        personRepository.save(personListLocal);
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

    public List<PersonDTO> findAllPersonsDTO() {
        l.debug("finding all persons - service");
        List<Person> personList;
        List<PersonDTO> personDTOList;
        personList = personRepository.findAll();
        personDTOList = mapper.mapAsList(personList, PersonDTO.class);
        l.info("found all persons - service: " + personList.toString());
        return personDTOList;
    }

    public Person findPersonByLogin(String login) {
        return personRepository.findByUsername(login);
    }

    public Person findPersonByName(String name) {
        return personRepository.findByName(name);
    }


    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        l.info("load user:" + username);
        Person user = personRepository.findByUsername(username);
        l.info("uživatel: " + user);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getUserRole());
        l.info("roile uživatele:" + authorities);
        return buildUserForAuthentication(user, authorities);

    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(Person user,
                                            List<GrantedAuthority> authorities) {
        l.info("build new user");
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        l.info(" builUserAuthority");
        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
        l.info("Granted autority: " + Result);
        return Result;
    }

    public Person findPersonByAuthentication(Authentication auth) {
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Person person = findPersonByLogin(userDetail.getUsername());
        return person;
    }

    private String hashPasswordForUser(String passwordUnhashed) {
        String passwordHashed;
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        passwordHashed = encoder.encode(passwordUnhashed);
        return passwordHashed;
    }

}
