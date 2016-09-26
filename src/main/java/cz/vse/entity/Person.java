package cz.vse.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.websocket.ClientEndpoint;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity(name = "osobsa")
public class Person extends BaseEntity {
    private String name;
    private String login;
    private String pass;
    private LocalDateTime createdDate;
    private LocalDateTime lastLogin;

//    @ManyToMany (mappedBy = "id")
//    private List<RoleEnum> roleEna;

    @ManyToMany //(mappedBy = "id")
    private List<TestProject> testProjectsMember;

}
