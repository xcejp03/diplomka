package cz.vse.service;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.RoleEnum;
import cz.vse.entity.UserRole;
import org.springframework.security.core.Authentication;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface RoleService {

    List<UserRole> findAllRoles();

    void createRoleForUser(UserRole userRole);

    List<UserRole> findAllUserRolesByUser(Person user);

    List<UserRole> findAllUserRolesByUserId(long id);

    void createRoleForUser(PersonDTO personDTO);

    void updateRoleForUser(PersonDTO personDTO);

    List<RoleEnum> findUsersRoleEnum(long id);

}
