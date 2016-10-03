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
public class TestStepInstance  extends BaseEntity{
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String action;
    private String expected;
    private String actual;

    @ManyToOne
    @JoinColumn (name = "testStepMuster_id")
    private TestStepMuster testStepMuster;

    @ManyToMany (mappedBy = "testStepInstances")
    private List<Defect> defects;

    @ManyToOne
    @JoinColumn (name = "testCaseInstance_id")
    private TestCaseInstance testCaseInstance;

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

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public TestStepMuster getTestStepMuster() {
        return testStepMuster;
    }

    public void setTestStepMuster(TestStepMuster testStepMuster) {
        this.testStepMuster = testStepMuster;
    }

    public List<Defect> getDefects() {
        return defects;
    }

    public void setDefects(List<Defect> defects) {
        this.defects = defects;
    }

    public TestCaseInstance getTestCaseInstance() {
        return testCaseInstance;
    }

    public void setTestCaseInstance(TestCaseInstance testCaseInstance) {
        this.testCaseInstance = testCaseInstance;
    }
}
