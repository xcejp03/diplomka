package cz.vse.utils;

import cz.vse.entity.Person;
import cz.vse.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by pcejk on 03.01.2017.
 */
@Component
public class SecurityUtils {
    @Autowired
    private PersonService personService;

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
}
