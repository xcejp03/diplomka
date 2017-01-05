package cz.vse.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class Defect extends BaseEntity {
    private String name;
    private String description;
    private PriorityDefectEnum priority;
    private DefectStatusEnum status;
    private String AffectsVersion;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;


    @ManyToMany  // (fetch = FetchType.EAGER)
    @JoinTable(name = "DEFECT_TCI", joinColumns = @JoinColumn(name = "DEFECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TCI_ID", referencedColumnName = "ID"))
    private List<TCInstance> tcInstances;
    @ManyToMany
    @JoinTable(name = "DEFECT_TSI", joinColumns = @JoinColumn(name = "DEFECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TSI_ID", referencedColumnName = "ID"))
    private List<TSInstance> tsInstances;

    @ManyToOne// (fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id")
    private Person assignee;

    @ManyToOne// (fetch = FetchType.EAGER)
    @JoinColumn(name = "reporter_id")
    private Person reporter;

    @ManyToOne
    @JoinColumn(name = "projectSource_id")
    private Project projectSource;

    @ManyToOne
    @JoinColumn(name = "tcInstanceSource_id")
    private TCInstance tcInstanceSource;

    @ManyToOne
    @JoinColumn(name = "tsInstanceSource_id")
    private TSInstance tsInstanceSource;

    @OneToMany(mappedBy = "defect")
    private List<DefectComment> defectCommentList;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityDefectEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityDefectEnum priority) {
        this.priority = priority;
    }

    public DefectStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DefectStatusEnum status) {
        this.status = status;
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

    public TCInstance getTcInstanceSource() {
        return tcInstanceSource;
    }

    public void setTcInstanceSource(TCInstance tcInstanceSource) {
        this.tcInstanceSource = tcInstanceSource;
    }

    public TSInstance getTsInstanceSource() {
        return tsInstanceSource;
    }

    public void setTsInstanceSource(TSInstance tsInstanceSource) {
        this.tsInstanceSource = tsInstanceSource;
    }

    public Project getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(Project projectSource) {
        this.projectSource = projectSource;
    }

    public List<DefectComment> getDefectCommentList() {
        return defectCommentList;
    }

    public void setDefectCommentList(List<DefectComment> defectCommentList) {
        this.defectCommentList = defectCommentList;
    }

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

    @Override
    public String toString() {
        return "Defect{" +
                "description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", AffectsVersion='" + AffectsVersion + '\'' +
//                ", TCInstances=" + tcInstances +
//                ", TSInstances=" + tsInstances +
//                ", assignee=" + assignee +
//                ", reporter=" + reporter +
                '}';
    }
}
