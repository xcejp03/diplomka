package cz.vse.dto;

import cz.vse.entity.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by pcejka on 11.01.2017.
 */
public class WorkTCDTO extends BaseDTO {
    private Long tcMuster_id;

    private Long assignee_id;

    private Long workList_id;

    private List<Long> tcRunHistoryList_id;

    private PriorityTCEnum priorityTCEnum;

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

    public PriorityTCEnum getPriorityTCEnum() {
        return priorityTCEnum;
    }

    public void setPriorityTCEnum(PriorityTCEnum priorityTCEnum) {
        this.priorityTCEnum = priorityTCEnum;
    }
}
