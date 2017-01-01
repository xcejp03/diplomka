package cz.vse.service;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.UserRole;
import org.springframework.security.core.Authentication;

import javax.management.relation.Role;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface RoleService {

    List<UserRole> findAllRoles();

    void createRoleForUser(UserRole userRole);

}
