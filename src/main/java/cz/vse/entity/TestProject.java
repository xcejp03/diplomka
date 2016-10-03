package cz.vse.entity;

import javax.persistence.*;
import java.security.acl.Owner;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TestProject  extends BaseEntity{
    private String name;

    @ManyToOne
    @JoinColumn (name = "projectOwner_id")
    private Person projectOwner;

    @ManyToMany (mappedBy = "testProjectsMember")
    private List<Person> projectMembers;

    @OneToMany (mappedBy = "testProject")
    private List<TestSuite> testSuites;

    @OneToMany (mappedBy = "testProject")
    private List<TestCaseMuster> testCaseMusters;

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

    public List<Person> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(List<Person> projectMembers) {
        this.projectMembers = projectMembers;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    public List<TestCaseMuster> getTestCaseMusters() {
        return testCaseMusters;
    }

    public void setTestCaseMusters(List<TestCaseMuster> testCaseMusters) {
        this.testCaseMusters = testCaseMusters;
    }
}
