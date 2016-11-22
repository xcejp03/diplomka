package cz.vse.entity;

import javax.persistence.*;
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

//    @OneToMany (mappedBy = "id")
//    private List<DefectComment> defectComments;

    @ManyToMany  // (fetch = FetchType.EAGER) // PROVĚŘIT SPRÁVNOST
    @JoinTable (name = "DEFECT_TCI", joinColumns = @JoinColumn (name = "DEFECT_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "TCI_ID", referencedColumnName = "ID"))
    private List<TCInstance> tcInstances;      //defect může být navázán TC nebo konkrétní step


    @ManyToMany //  (fetch = FetchType.EAGER) // PROVĚŘIT SPRÁVNOST
    @JoinTable (name = "DEFECT_TSI", joinColumns = @JoinColumn (name = "DEFECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TSI_ID", referencedColumnName = "ID"))
    private List<TSInstance> tsInstances;      //nebo může být navázáno na oboje



    @ManyToOne// (fetch = FetchType.EAGER)
    @JoinColumn (name = "assignee_id")
    private Person assignee;

    @ManyToOne// (fetch = FetchType.EAGER)
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

    public List<TCInstance> getTcInstances() {
        return tcInstances;
    }

    public void setTcInstances(List<TCInstance> TCInstances) {
        this.tcInstances = TCInstances;
    }

    public List<TSInstance> getTsInstances() {
        return tsInstances;
    }

    public void setTsInstances(List<TSInstance> TSInstances) {
        this.tsInstances = TSInstances;
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

    @Override
    public String toString() {
        return "Defect{" +
                "description='" + description + '\'' +
                ", priorityEnum=" + priorityEnum +
                ", defectStatusEnum=" + defectStatusEnum +
                ", AffectsVersion='" + AffectsVersion + '\'' +
//                ", TCInstances=" + tcInstances +
//                ", TSInstances=" + tsInstances +
//                ", assignee=" + assignee +
//                ", reporter=" + reporter +
                '}';
    }
}
