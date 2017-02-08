package cz.vse.dto;

import cz.vse.entity.DefectStatusEnum;
import cz.vse.entity.PriorityDefectEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by pcejka on 10.10.2016.
 */
public class DefectForm extends BaseDTO {
    @Size(min = 1, max = 50)
    private String name;
    @Size(min = 1, max = 500)
    private String description;
    @Size(min = 1, max = 50)
    private String affectsVersion;
    @NotNull
    private PriorityDefectEnum priority;
    private DefectStatusEnum status;
//    private String createdDateTime;
//    private String updatedDateTime;
    @NotNull
    private Long assignee_id;
    private Long reporter_id;

    private Long projectSource_id;
    private Long tcInstanceSource_id;
    private Long tsInstanceSource_id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAffectsVersion() {
        return affectsVersion;
    }

    public void setAffectsVersion(String affectsVersion) {
        this.affectsVersion = affectsVersion;
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

    public Long getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(Long assignee_id) {
        this.assignee_id = assignee_id;
    }

    public Long getReporter_id() {
        return reporter_id;
    }

    public void setReporter_id(Long reporter_id) {
        this.reporter_id = reporter_id;
    }

    public Long getProjectSource_id() {
        return projectSource_id;
    }

    public void setProjectSource_id(Long projectSource_id) {
        this.projectSource_id = projectSource_id;
    }

    public Long getTcInstanceSource_id() {
        return tcInstanceSource_id;
    }

    public void setTcInstanceSource_id(Long tcInstanceSource_id) {
        this.tcInstanceSource_id = tcInstanceSource_id;
    }

    public Long getTsInstanceSource_id() {
        return tsInstanceSource_id;
    }

    public void setTsInstanceSource_id(Long tsInstanceSource_id) {
        this.tsInstanceSource_id = tsInstanceSource_id;
    }

    @Override
    public String toString() {
        return "DefectForm{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", affectsVersion='" + affectsVersion + '\'' +
                ", priority=" + priority +
                ", assignee_id=" + assignee_id +
                ", projectSource_id=" + projectSource_id +
                ", tcInstanceSource_id=" + tcInstanceSource_id +
                ", tsInstanceSource_id=" + tsInstanceSource_id +
                '}';
    }
}

