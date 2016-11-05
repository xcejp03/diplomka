package cz.vse.entity;

import javax.persistence.*;
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
    private List<Person> testProjectMembers;

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

    public List<Person> getTestProjectMembers() {
        return testProjectMembers;
    }

    public void setTestProjectMembers(List<Person> projectMembers) {
        this.testProjectMembers = projectMembers;
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

    @Override
    public String toString() {
        return "TestProject{" +
                "name='" + name + '\'' +
//                ", projectOwner=" + projectOwner +
//                ", projectMembers=" + projectMembers +
//                ", testSuites=" + testSuites +
//                ", testCaseMusters=" + testCaseMusters +
                '}';
    }
}
