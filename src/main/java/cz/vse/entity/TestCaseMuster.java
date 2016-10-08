package cz.vse.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 * <p>
 * Při exekuci se natáhnou stepy vyplěněny jak je napsán bez výsledků testů. Stepy se budou po spuštněí
 * ukládat jako nové instance. V db pak bude - NE! bude to jinak.
 */
@Entity
public class TestCaseMuster extends BaseEntity {
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;


    @OneToMany (mappedBy = "testCaseMuster")
    private List<TestCaseInstance> testCaseInstances;

    @ManyToOne
    @JoinColumn (name = "testProject_id")
    private TestProject testProject;


    @ManyToMany
    @JoinTable (name = "TCMUSTER_TESTSUITE", joinColumns = @JoinColumn (name = "TCMUSTER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TESTSUITE_ID", referencedColumnName = "ID"))
    private List<TestSuite> testSuites;

    @ManyToMany    // PROVĚŘIT SPRÁVNOST
    @JoinTable (name = "TSMUSTER_TSMUSTER", joinColumns = @JoinColumn (name = "TCMUSTER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TSMUSTER_ID", referencedColumnName = "ID"))
    private List<TestStepMuster> testStepMusters;

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

    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public List<TestCaseInstance> getTestCaseInstances() {
        return testCaseInstances;
    }

    public void setTestCaseInstances(List<TestCaseInstance> testCaseInstances) {
        this.testCaseInstances = testCaseInstances;
    }

    public TestProject getTestProject() {
        return testProject;
    }

    public void setTestProject(TestProject testProject) {
        this.testProject = testProject;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    public List<TestStepMuster> getTestStepMusters() {
        return testStepMusters;
    }

    public void setTestStepMusters(List<TestStepMuster> testStepMusters) {
        this.testStepMusters = testStepMusters;
    }

    @Override
    public String toString() {
        return "TestCaseMuster{" +
                "name='" + name + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", testCaseInstances=" + testCaseInstances +
                ", testProject=" + testProject +
                ", testSuites=" + testSuites +
                ", testStepMusters=" + testStepMusters +
                '}';
    }
}
