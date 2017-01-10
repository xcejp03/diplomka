package cz.vse.dto;

import cz.vse.entity.PriorityTCEnum;
import cz.vse.entity.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TCMusterDTO extends BaseDTO {

    private String name;
    private Long project_id;
    private List<Long> tsMusters_id;
    private List<Long> tcInstances_id;
    private List<Long> testSuite_id;
    private StatusEnum status;
    private PriorityTCEnum priority;
    private LocalDateTime createdDateTime;
    private String prerequisite;
    private String note;

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TCMusterDTO)) return false;
        if (!super.equals(o)) return false;

        TCMusterDTO that = (TCMusterDTO) o;

        if (getCreatedDateTime() != null ? !getCreatedDateTime().equals(that.getCreatedDateTime()) : that.getCreatedDateTime() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return project_id != null ? project_id.equals(that.project_id) : that.project_id == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCreatedDateTime() != null ? getCreatedDateTime().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (project_id != null ? project_id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TCMusterDTO{" +
                "createdDateTime=" + createdDateTime +
                ", name='" + name + '\'' +
                ", project_id=" + project_id +
                '}';
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }
}