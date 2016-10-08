package cz.vse.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TestStepMuster extends BaseEntity {
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String action;
    private String expected;

    @ManyToMany //(mappedBy = "")
    private List<Defect> defects;

    @OneToMany (mappedBy = "testStepMuster")
    private List<TestStepInstance> testStepInstances;

    @ManyToMany (mappedBy = "testStepMusters")
    private List<TestCaseMuster> testCaseMusters;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public List<Defect> getDefects() {
        return defects;
    }

    public void setDefects(List<Defect> defects) {
        this.defects = defects;
    }

    public List<TestStepInstance> getTestStepInstances() {
        return testStepInstances;
    }

    public void setTestStepInstances(List<TestStepInstance> testStepInstances) {
        this.testStepInstances = testStepInstances;
    }

    public List<TestCaseMuster> getTestCaseMusters() {
        return testCaseMusters;
    }

    public void setTestCaseMusters(List<TestCaseMuster> testCaseMusters) {
        this.testCaseMusters = testCaseMusters;
    }

    @Override
    public String toString() {
        return "TestStepMuster{" +
                "createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", action='" + action + '\'' +
                ", expected='" + expected + '\'' +
                ", defects=" + defects +
                ", testStepInstances=" + testStepInstances +
                ", testCaseMusters=" + testCaseMusters +
                '}';
    }
}
