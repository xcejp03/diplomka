package cz.vse.dto;

import cz.vse.entity.PriorityTCEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by pcejka on 11.01.2017.
 */
public class WorkTCForm extends BaseDTO {
    @Size(min = 1, max = 50)
    private String name;
    private Long tcMuster_id;
    private Long assignee_id;
    private Long workList_id;
    private List<Long> tcRunHistoryList_id;
    @NotNull
    private PriorityTCEnum priority;
    private String updatedDateTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTcMuster_id() {
        return tcMuster_id;
    }

    public void setTcMuster_id(Long tcMuster_id) {
        this.tcMuster_id = tcMuster_id;
    }

    public Long getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(Long assignee_id) {
        this.assignee_id = assignee_id;
    }

    public Long getWorkList_id() {
        return workList_id;
    }

    public void setWorkList_id(Long workList_id) {
        this.workList_id = workList_id;
    }

    public List<Long> getTcRunHistoryList_id() {
        return tcRunHistoryList_id;
    }

    public void setTcRunHistoryList_id(List<Long> tcRunHistoryList_id) {
        this.tcRunHistoryList_id = tcRunHistoryList_id;
    }

    public PriorityTCEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityTCEnum priority) {
        this.priority = priority;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
