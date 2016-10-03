package cz.vse.entity;

import javax.persistence.*;
import java.sql.Clob;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class Defect extends BaseEntity {
    private String description;
    private PriorityEnum priorityEnum;
    private DefectStatusEnum defectStatusEnum;
    private String AffectsVersion;
//    private Clob file;

    @OneToMany (mappedBy = "id")
    private List<DefectComment> defectComments;

    @ManyToMany    // PROVĚŘIT SPRÁVNOST
    @JoinTable (name = "DEFECT_TCI", joinColumns = @JoinColumn (name = "DEFECT_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "TCI_ID", referencedColumnName = "ID"))
    private List<TestCaseInstance> testCaseInstances;      //defect může být navázán TC nebo konkrétní step


    @ManyToMany    // PROVĚŘIT SPRÁVNOST
    @JoinTable (name = "DEFECT_TSI", joinColumns = @JoinColumn (name = "DEFECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TSI_ID", referencedColumnName = "ID"))
    private List<TestStepInstance> testStepInstances;      //nebo může být navázáno na oboje



    @ManyToOne
    @JoinColumn (name = "assignee_id")
    private Person assignee;

    @ManyToOne
    @JoinColumn (name = "reporter_id")
    private Person reporter;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityEnum getPriorityEnum() {
        return priorityEnum;
    }

    public void setPriorityEnum(PriorityEnum priorityEnum) {
        this.priorityEnum = priorityEnum;
    }

    public DefectStatusEnum getDefectStatusEnum() {
        return defectStatusEnum;
    }

    public void setDefectStatusEnum(DefectStatusEnum defectStatusEnum) {
        this.defectStatusEnum = defectStatusEnum;
    }

    public String getAffectsVersion() {
        return AffectsVersion;
    }

    public void setAffectsVersion(String affectsVersion) {
        AffectsVersion = affectsVersion;
    }

    public List<DefectComment> getDefectComments() {
        return defectComments;
    }

    public void setDefectComments(List<DefectComment> defectComments) {
        this.defectComments = defectComments;
    }

    public List<TestCaseInstance> getTestCaseInstances() {
        return testCaseInstances;
    }

    public void setTestCaseInstances(List<TestCaseInstance> testCaseInstances) {
        this.testCaseInstances = testCaseInstances;
    }

    public List<TestStepInstance> getTestStepInstances() {
        return testStepInstances;
    }

    public void setTestStepInstances(List<TestStepInstance> testStepInstances) {
        this.testStepInstances = testStepInstances;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public Person getReporter() {
        return reporter;
    }

    public void setReporter(Person reporter) {
        this.reporter = reporter;
    }
}
