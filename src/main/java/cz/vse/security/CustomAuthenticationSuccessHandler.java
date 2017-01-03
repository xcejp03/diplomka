package cz.vse.security;

import cz.vse.entity.Person;
import cz.vse.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by pcejk on 03.01.2017.
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private PersonService personService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.debug("Logování přihlášení uživatele");
        super.onAuthenticationSuccess(request, response, authentication);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.findPersonByAuthentication(auth);
        person.setLastLogged(LocalDateTime.now());
        personService.updatePerson(person);
    }
}
