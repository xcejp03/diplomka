package cz.vse.entity;

import javax.persistence.Entity;
import javax.websocket.ClientEndpoint;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity (name = "osobsa")
public class Person extends BaseEntity{
    private String name;
    private String login;
    private String pass;
    private LocalDateTime createdDate;
    private LocalDateTime lastLogin;
    private List<RoleEnum> roleEna;
    private List<TestProject>testProjects;

}
