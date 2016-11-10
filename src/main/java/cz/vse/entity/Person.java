package cz.vse.entity;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import javax.websocket.ClientEndpoint;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity(name = "Person")
public class Person extends BaseEntity {
    private String name;
    private String login;
    private String pass;
    private LocalDateTime createdDate;
    private LocalDateTime lastLogin;

//    @ManyToMany (mappedBy = "id")
//    private List<RoleEnum> roleEna;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "PERSON_TESTPROJECT",
            joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TESTPROJECT_ID", referencedColumnName = "ID"))
    private List<TestProject> testProjectsMember;

    @OneToMany(mappedBy = "projectOwner")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<TestProject> getTestProjectsMember() {
        return testProjectsMember;
    }

    public void setTestProjectsMember(List<TestProject> testProjectsMember) {
        this.testProjectsMember = testProjectsMember;
    }

    public List<TestProject> getTestProjectsOwner() {
        return testProjectsOwner;
    }

    public void setTestProjectsOwner(List<TestProject> testProjectsOwner) {
        this.testProjectsOwner = testProjectsOwner;
    }

    public List<Defect> getDefectsAssignee() {
        return defectsAssignee;
    }

    public void setDefectsAssignee(List<Defect> defectsAssignee) {
        this.defectsAssignee = defectsAssignee;
    }

    public List<Defect> getDefectsReporter() {
        return defectsReporter;
    }

    public void setDefectsReporter(List<Defect> defectsReporter) {
        this.defectsReporter = defectsReporter;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", createdDate=" + createdDate +
                ", lastLogin=" + lastLogin +
                ", testProjectsMember=" + testProjectsMember +
                ", testProjectsOwner=" + testProjectsOwner +
                ", defectsAssignee=" + defectsAssignee +
                ", defectsReporter=" + defectsReporter +
                '}';
    }
}
