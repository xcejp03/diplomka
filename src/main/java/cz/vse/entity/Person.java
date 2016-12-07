package cz.vse.entity;

import javax.persistence.*;
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

    private String testPole;

//    @ManyToMany (mappedBy = "id")
//    private List<RoleEnum> roleEna;

    @ManyToMany //(cascade = CascadeType.ALL)
    @JoinTable(name = "PERSON_PROJECT",
            joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID"))
    private List<Project> projectsMember;

    @OneToMany(mappedBy = "projectOwner")
    private List<Project> projectOwners;

    @ManyToMany
    @JoinTable(name = "PERSON_DEFECTASSIGNEE",
            joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DEFECT_ID", referencedColumnName = "ID"))
    private List<Defect> defectAssignees;

    @ManyToMany
    @JoinTable(name = "PERSON_DEFECTREPORTER",
            joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DEFECT_ID", referencedColumnName = "ID"))
    private List<Defect> defectReporters;

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

    public List<Project> getProjectsMember() {
        return projectsMember;
    }

    public void setProjectsMember(List<Project> projectsMember) {
        this.projectsMember = projectsMember;
    }

    public void addTestProjectMember(Project project) {
        this.projectsMember.add(project);
    }

    public List<Project> getProjectOwners() {
        return projectOwners;
    }

    public void setProjectOwners(List<Project> projectsOwner) {
        this.projectOwners = projectsOwner;
    }

    public List<Defect> getDefectAssignees() {
        return defectAssignees;
    }

    public void setDefectAssignees(List<Defect> defectsAssignee) {
        this.defectAssignees = defectsAssignee;
    }

    public List<Defect> getDefectReporters() {
        return defectReporters;
    }

    public void setDefectReporters(List<Defect> defectsReporter) {
        this.defectReporters = defectsReporter;
    }

    public String getTestPole() {
        return testPole;
    }

    public void setTestPole(String testPole) {
        this.testPole = testPole;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
//                ", createdDate=" + createdDate +
//                ", lastLogin=" + lastLogin +
//                ", projectsMember=" + projectsMember +
//                ", projectsOwner=" + projectOwners +
//                ", defectsAssignee=" + defectAssignees +
//                ", defectsReporter=" + defectReporters +
                '}';
    }
}
