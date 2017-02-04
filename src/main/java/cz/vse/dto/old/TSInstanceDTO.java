package cz.vse.dto.old;

import cz.vse.dto.*;
import cz.vse.entity.StatusEnum;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TSInstanceDTO extends cz.vse.dto.BaseDTO {

    private String action;
    private String expected;
    private Long tsMuster_id;
    private Long tcInstance_id;
    private String result;
    private String tester_id;
    private StatusEnum status;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public Long getTsMuster_id() {
        return tsMuster_id;
    }

    public void setTsMuster_id(Long tsMuster_id) {
        this.tsMuster_id = tsMuster_id;
    }

    public Long getTcInstance_id() {
        return tcInstance_id;
    }

    public void setTcInstance_id(Long tcInstance_id) {
        this.tcInstance_id = tcInstance_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTester_id() {
        return tester_id;
    }

    public void setTester_id(String tester_id) {
        this.tester_id = tester_id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}