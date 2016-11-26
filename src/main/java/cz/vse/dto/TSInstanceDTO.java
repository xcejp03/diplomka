package cz.vse.dto;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TSInstanceDTO extends BaseDTO {

    private String action;
    private String expected;
    private Long tsMuster_id;
    private Long tcInstance_id;

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
}