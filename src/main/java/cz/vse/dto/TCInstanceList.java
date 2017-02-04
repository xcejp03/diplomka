package cz.vse.dto;

import cz.vse.entity.StatusEnum;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TCInstanceList extends BaseDTO {

    private String name;
    private Long tcMuster_id;
    private List<Long> tsInstances_id;
    private Long tester_id;
    private StatusEnum status;
    private String createdDateTime;
    private String prerequisite;
    private String note;

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

    public List<Long> getTsInstances_id() {
        return tsInstances_id;
    }

    public void setTsInstances_id(List<Long> tsInstances_id) {
        this.tsInstances_id = tsInstances_id;
    }

    public Long getTester_id() {
        return tester_id;
    }

    public void setTester_id(Long tester_id) {
        this.tester_id = tester_id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
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
}