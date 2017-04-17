package cz.vse.utils;

import cz.vse.dto.PersonName;
import cz.vse.entity.Person;
import cz.vse.service.PersonService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by pcejk on 03.01.2017.
 */
@Component
public class SecurityUtils {
    @Autowired
    private PersonService personService;

    @Autowired
    private MapperFacade mapper;

    public Person getLoggedPerson() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByAuthentication(auth);
        return person;
    }

    public Long getLoggedPersonId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByAuthentication(auth);
        Long personId = person.getId();
        return personId;
    }


    public PersonName getLoggedPersonName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByAuthentication(auth);
        PersonName personName;
        personName = mapper.map(person, PersonName.class);
        return personName;
    }

    public Collection<? extends GrantedAuthority> getLoggedPersonAuthorities() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities();
    }
}
