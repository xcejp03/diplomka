package cz.vse.service.impl;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.UserRole;
import cz.vse.repository.PersonRepository;
import cz.vse.repository.UserRoleRepository;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.RoleService;
import ma.glasnost.orika.MapperFacade;
import org.apache.catalina.Role;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> findAllRoles() {
        List<UserRole> userRoleList;
        userRoleList = userRoleRepository.findAll();
        return userRoleList;
    }

    public void createRoleForUser(UserRole userRole)    {
        userRoleRepository.save(userRole);
    }
}



