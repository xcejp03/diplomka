package cz.vse.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TestSuite extends BaseEntity {
    private String name;        //testsuit je možné si pojmenovat
    private LocalDateTime createdDateTime;          // datum vytvoření testsuitu;
    private LocalDateTime updateDateTime;

    @ManyToOne
    @JoinColumn (name = "testProject_id")
    private TestProject testProject;        //testsuit patří pod jeden projekt

    @ManyToMany  (mappedBy = "testSuites")
    private List<TestCaseMuster> testCaseMusters;      //testsuit se skládá z testcasů;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public TestProject getTestProject() {
        return testProject;
    }

    public void setTestProject(TestProject testProject) {
        this.testProject = testProject;
    }

    public List<TestCaseMuster> getTestCaseMusters() {
        return testCaseMusters;
    }

    public void setTestCaseMusters(List<TestCaseMuster> testCaseMusters) {
        this.testCaseMusters = testCaseMusters;
    }

    @Override
    public String toString() {
        return "TestSuite{" +
                "name='" + name + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updateDateTime=" + updateDateTime +
                ", testProject=" + testProject +
                ", testCaseMusters=" + testCaseMusters +
                '}';
    }
}
