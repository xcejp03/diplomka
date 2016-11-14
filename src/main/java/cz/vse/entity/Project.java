package cz.vse.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class Project extends BaseEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "projectOwner_id")
    private Person projectOwner;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "projectsMember")
    private List<Person> personMembers;

    @OneToMany(mappedBy = "project")
    private List<TestSuite> testSuites;

    @OneToMany(mappedBy = "project")
    private List<TCMuster> TCMusters;

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

    public List<TCMuster> getTCMusters() {
        return TCMusters;
    }

    public void setTCMusters(List<TCMuster> TCMusters) {
        this.TCMusters = TCMusters;
    }


    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
//                ", projectOwner=" + projectOwner +
//                ", projectMembers=" + projectMembers +
//                ", testSuites=" + testSuites +
//                ", TCMusters=" + TCMusters +
                '}';
    }
}
