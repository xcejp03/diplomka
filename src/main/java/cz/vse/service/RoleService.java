package cz.vse.service;

import cz.vse.dto.PersonForm;
import cz.vse.dto.old.PersonDTO;
import cz.vse.entity.Person;
import cz.vse.entity.RoleEnum;
import cz.vse.entity.UserRole;

import java.util.List;

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

    void updateRoleForUser(PersonForm personForm);

    List<RoleEnum> findUsersRoleEnum(long id);

}
