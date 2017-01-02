package cz.vse.dto;

import cz.vse.entity.Person;
import cz.vse.entity.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TCInstanceDTO extends BaseDTO {

    private String name;
    private Long tcMuster_id;
    private List<Long> tsInstances_id;
    private Long tester_id;
    private StatusEnum status;
    private LocalDateTime createdDateTime;

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

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}