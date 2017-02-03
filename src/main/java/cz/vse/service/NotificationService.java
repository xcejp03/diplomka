package cz.vse.service;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.RoleEnum;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);

}
