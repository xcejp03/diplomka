package cz.vse.dto;

import cz.vse.entity.DefectStatusEnum;
import cz.vse.entity.PriorityDefectEnum;

import java.time.LocalDateTime;

/**
 * Created by pcejka on 10.10.2016.
 */
public class DefectDTO extends BaseDTO {
    private String name;
    private String description;
    private String affectsVersion;
    private PriorityDefectEnum priority;
    private DefectStatusEnum status;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private Long assignee_id;
    private Long reporter_id;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

