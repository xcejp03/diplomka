package cz.vse.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class Project extends BaseEntity {
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "projectOwner_id")
    private Person projectOwner;

    @ManyToMany (cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "PERSON_PROJECT",
            joinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"))
    private List<Person> personMembers;

    @OneToMany(mappedBy = "project")
    private List<TestSuite> testSuites;

    @OneToMany(mappedBy = "project")
    private List<TCMuster> tcMusters;

    @OneToMany (mappedBy = "projectSource")
    private List<Defect> defectList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(Person projectOwner) {
        this.projectOwner = projectOwner;
    }

    public List<Person> getPersonMembers() {
        return personMembers;
    }

    public void setPersonMembers(List<Person> personMembers) {
        this.personMembers = personMembers;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    public List<TCMuster> getTcMusters() {
        return tcMusters;
    }

    public void setTcMusters(List<TCMuster> TCMusters) {
        this.tcMusters = TCMusters;
    }

    public List<Defect> getDefectList() {
        return defectList;
    }

    public void setDefectList(List<Defect> defectList) {
        this.defectList = defectList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }
}
