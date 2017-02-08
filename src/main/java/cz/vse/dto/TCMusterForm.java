package cz.vse.dto;

import cz.vse.entity.PriorityTCEnum;
import cz.vse.entity.StatusEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TCMusterForm extends BaseDTO {
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    private Long project_id;
    private String project;
    private Long author_id;
    private List<Long> tsMusters_id;
    private List<Long> tcInstances_id;
    private List<Long> testSuite_id;
    private StatusEnum status;
    @NotNull
    private PriorityTCEnum priority;
    private String createdDateTime;
    @Size(max = 500)
    private String prerequisite;
    @Size(max = 500)
    private String note;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
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

    @Override
    public String toString() {
        return "TCMusterDTO{" +
                "createdDateTime=" + createdDateTime +
                ", name='" + name + '\'' +
                ", project_id=" + project_id +
                '}';
    }

}