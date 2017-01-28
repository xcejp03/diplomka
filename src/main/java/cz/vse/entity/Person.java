package cz.vse.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class Person extends BaseEntity {
    private String name;
    private String username;
    private String password;
    private LocalDateTime lastLogged;
    private boolean enabled;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<>(0);


    @ManyToMany(mappedBy = "personMembers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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


    @OneToMany(mappedBy = "assignee")
    private List<WorkTC> workTCAssignee;

    @OneToMany(mappedBy = "author")
    private List<WorkList> workListAuthor;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastLogged() {
        return lastLogged;
    }

    public void setLastLogged(LocalDateTime lastLogged) {
        this.lastLogged = lastLogged;
    }

    public List<Project> getProjectsMember() {
        return projectsMember;
    }

    public void setProjectsMember(List<Project> projectsMember) {
        this.projectsMember = projectsMember;
    }

    public List<Project> getProjectOwners() {
        return projectOwners;
    }

    public void setProjectOwners(List<Project> projectOwners) {
        this.projectOwners = projectOwners;
    }

    public List<Defect> getDefectAssignees() {
        return defectAssignees;
    }

    public void setDefectAssignees(List<Defect> defectAssignees) {
        this.defectAssignees = defectAssignees;
    }

    public List<Defect> getDefectReporters() {
        return defectReporters;
    }

    public void setDefectReporters(List<Defect> defectReporters) {
        this.defectReporters = defectReporters;
    }

    public List<WorkTC> getWorkTCAssignee() {
        return workTCAssignee;
    }

    public void setWorkTCAssignee(List<WorkTC> workTCAssignee) {
        this.workTCAssignee = workTCAssignee;
    }

    public List<WorkList> getWorkListAuthor() {
        return workListAuthor;
    }

    public void setWorkListAuthor(List<WorkList> workListAuthor) {
        this.workListAuthor = workListAuthor;
    }

    public Person() {
    }

    public Person(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public Person(String username, String password, boolean enabled, Set<UserRole> userRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (username != null ? !username.equals(person.username) : person.username != null) return false;
        return password != null ? password.equals(person.password) : person.password == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
