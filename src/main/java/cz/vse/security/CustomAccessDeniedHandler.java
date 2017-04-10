package cz.vse.security;

import cz.vse.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pcejk on 03.01.2017.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private PersonService personService;
    private String errorPage;


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException el) throws IOException, ServletException {
        l.error("acces denied - handler");
        l.error("el: " + el);
        httpServletResponse.sendError(403, el.getMessage());
    }
}