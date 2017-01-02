package cz.vse.dto;

import cz.vse.entity.StatusEnum;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TSInstanceRunDTO extends BaseDTO {

    private String action;
    private String expected;
    private String result;
    private Long tcInstance_id;
    private Long tester_id;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getTcInstance_id() {
        return tcInstance_id;
    }

    public void setTcInstance_id(Long tcInstance_id) {
        this.tcInstance_id = tcInstance_id;
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
}