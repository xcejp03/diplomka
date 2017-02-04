package cz.vse.dto;

import cz.vse.entity.DefectStatusEnum;
import cz.vse.entity.PriorityDefectEnum;

/**
 * Created by pcejka on 10.10.2016.
 */
public class DefectList extends BaseDTO {
    private String name;
    private String description;
    private DefectStatusEnum status;
    private PriorityDefectEnum priority;
    private String updatedDateTime;
    private Long assignee_id;


//    private Long reporter_id;
//    private String createdDateTime;
//    private String affectsVersion;
//    private Long projectSource_id;
//    private Long tcInstanceSource_id;
//    private Long tsInstanceSource_id;


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

    @Override
    public String toString() {
        return "DefectList{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", updatedDateTime='" + updatedDateTime + '\'' +
                ", assignee_id=" + assignee_id +
                '}';
    }
}

