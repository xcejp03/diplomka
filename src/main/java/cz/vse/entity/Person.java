package cz.vse.entity;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import javax.websocket.ClientEndpoint;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity(name = "person")
public class Person extends BaseEntity {
    private String name;
    private String login;
    private String pass;
    private LocalDateTime createdDate;
    private LocalDateTime lastLogin;

//    @ManyToMany (mappedBy = "id")
//    private List<RoleEnum> roleEna;

    @ManyToMany
    @JoinTable(name = "PERSON_TESTPROJECT",
            joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TESTPROJECT_ID", referencedColumnName = "ID"))
    private List<TestProject> testProjectsMember;

    @OneToMany (mappedBy = "projectOwner")
    private List<TestProject> testProjectsOwner;

    @ManyToMany
    @JoinTable(name = "PERSON_DEFECTASSIGNEE",
            joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DEFECT_ID", referencedColumnName = "ID"))
    private List<Defect> defectsAssignee;

    @ManyToMany
    @JoinTable(name = "PERSON_DEFECTREPORTER",
            joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DEFECT_ID", referencedColumnName = "ID"))
    private List<Defect> defectsReporter;
}
