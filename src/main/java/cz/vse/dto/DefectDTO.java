package cz.vse.dto;

import cz.vse.entity.DefectStatusEnum;
import cz.vse.entity.PriorityDefectEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by pcejka on 10.10.2016.
 */
public class DefectDTO extends BaseDTO {
    private String name;
    private String description;
    private String affectsVersion;
    private PriorityDefectEnum priority;
    private DefectStatusEnum status;
    private String createdDateTime;
    private String updatedDateTime;
    private Long assignee_id;
    private String assignee;
    private Long reporter_id;
    private String reporter;

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

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public Long getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(Long assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Long getReporter_id() {
        return reporter_id;
    }

    public void setReporter_id(Long reporter_id) {
        this.reporter_id = reporter_id;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefectDTO)) return false;
        if (!super.equals(o)) return false;

        DefectDTO defectDTO = (DefectDTO) o;

        if (getDescription() != null ? !getDescription().equals(defectDTO.getDescription()) : defectDTO.getDescription() != null)
            return false;
        return getAffectsVersion() != null ? getAffectsVersion().equals(defectDTO.getAffectsVersion()) : defectDTO.getAffectsVersion() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getAffectsVersion() != null ? getAffectsVersion().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DefectDTO{" +
                "description='" + description + '\'' +
                ", affectsVersion='" + affectsVersion + '\'' +
                '}';
    }
}

