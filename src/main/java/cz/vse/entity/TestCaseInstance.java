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
public class TestCaseInstance extends BaseEntity {
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    @ManyToOne
    @JoinColumn (name = "testCaseMuster_id")
    private TestCaseMuster testCaseMuster;

    @ManyToMany (mappedBy = "testCaseInstances")
    private List<Defect> defects;

    @OneToMany (mappedBy = "testCaseInstance")
    private List<TestStepInstance> testStepInstances;

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

    public TestCaseMuster getTestCaseMuster() {
        return testCaseMuster;
    }

    public void setTestCaseMuster(TestCaseMuster testCaseMuster) {
        this.testCaseMuster = testCaseMuster;
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

    @Override
    public String toString() {
        return "TestCaseInstance{" +
                "name='" + name + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", testCaseMuster=" + testCaseMuster +
                ", defects=" + defects +
                ", testStepInstances=" + testStepInstances +
                '}';
    }
}
