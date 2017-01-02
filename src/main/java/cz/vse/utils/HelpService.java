package cz.vse.utils;

/**
 * Created by pcejk on 29.12.2016.
 */

import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.UserRole;
import cz.vse.repository.PersonRepository;
import cz.vse.repository.ProjectRepository;
import cz.vse.repository.UserRoleRepository;
import cz.vse.service.PersonService;
import cz.vse.service.RoleService;
import cz.vse.service.UserDetailService;
import cz.vse.service.impl.PersonServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class HelpService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleRepository userRoleRepository;

//    @Autowired
//    private UserDetailService manager;

    @Autowired
    private PersonService personService;

// use in your method

    public void prihlasit() {
        Person user = personService.findPersonByLogin("alfred");
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }

            @Override
            public String getPassword() {
                return "$2a$10$Toj6YO9uw2pEAu9E6Iv/Y.btTU5sXCkrCePaleZkzmivW3NlRmLfe";
            }

            @Override
            public String getUsername() {
                return "alfred";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null , authorities);

        SecurityContextHolder.getContext().setAuthentication(auth);
        l.warn(auth);

    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        l.info(" builUserAuthority");
        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole().getRoleString()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
        l.info("Granted autority: " + Result);
        return Result;
    }

    private User buildUserForAuthentication(Person user,
                                            List<GrantedAuthority> authorities) {
        l.info("build new user");
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    public void userRoleTesty() {
        Person person = personRepository.findById(100);
        l.info(person);
        person.getUserRole().clear();
        personRepository.save(person);

        userRoleRepository.delete(person.getUserRole());

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long saveProject(Project project) {
//        project.getPersonMembers().clear();
        List<Person> people = new ArrayList<>();
        for (Person person : project.getPersonMembers()) {

            people.add(personRepository.findOne(person.getId()));
        }
        Person owner = personRepository.findOne(project.getProjectOwner().getId());
        project.setPersonMembers(people);
        project.setProjectOwner(owner);
        Project savedProject = projectRepository.save(project);

        return savedProject.getId();
    }

}
