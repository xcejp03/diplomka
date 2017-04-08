package cz.vse.dto;

import cz.vse.entity.PriorityTCEnum;
import cz.vse.entity.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TCMusterList extends BaseDTO {

    private String name;
    private Long project_id;
    private Long author_id;
    private List<Long> tsMusters_id;
    private List<Long> tcInstances_id;
    private List<Long> testSuite_id;
    private StatusEnum status;
    private PriorityTCEnum priority;
    private String createdDateTime;
    private String prerequisite;
    private String note;
    private String lastRunDateTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public List<Long> getTsMusters_id() {
        return tsMusters_id;
    }

    public void setTsMusters_id(List<Long> tsMusters_id) {
        this.tsMusters_id = tsMusters_id;
    }

    public List<Long> getTcInstances_id() {
        return tcInstances_id;
    }

    public void setTcInstances_id(List<Long> tcInstances_id) {
        this.tcInstances_id = tcInstances_id;
    }

    public List<Long> getTestSuite_id() {
        return testSuite_id;
    }

    public void setTestSuite_id(List<Long> testSuite_id) {
        this.testSuite_id = testSuite_id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public PriorityTCEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityTCEnum priority) {
        this.priority = priority;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastRunDateTime() {
        return lastRunDateTime;
    }

    public void setLastRunDateTime(String lastRunDateTime) {
        this.lastRunDateTime = lastRunDateTime;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    @Override
    public String toString() {
        return "TCMusterList{" +
                "name='" + name + '\'' +
                ", project_id=" + project_id +
                ", author_id=" + author_id +
                ", status=" + status +
                ", priority=" + priority +
                ", note='" + note + '\'' +
                ", lastRunDateTime=" + lastRunDateTime +
                '}';
    }
}