package cz.vse.dto;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TSMusterForm extends BaseDTO {

    private String createdDateTime;
    private String updatedDateTime;
    private String action;
    private String expected;
    private Long author_id;

    private List<Long> defects_id;

    private List<Long> tsInstances_id;

    private Long tcMuster_id;

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

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

    public List<Long> getDefects_id() {
        return defects_id;
    }

    public void setDefects_id(List<Long> defects_id) {
        this.defects_id = defects_id;
    }

    public List<Long> getTsInstances_id() {
        return tsInstances_id;
    }

    public void setTsInstances_id(List<Long> tsInstances_id) {
        this.tsInstances_id = tsInstances_id;
    }

    public Long getTcMuster_id() {
        return tcMuster_id;
    }

    public void setTcMuster_id(Long tcMuster_id) {
        this.tcMuster_id = tcMuster_id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "TSMusterDTO{" +
                "createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", action='" + action + '\'' +
                ", expected='" + expected + '\'' +
                ", author_id=" + author_id +
                ", defects_id=" + defects_id +
                ", tsInstances_id=" + tsInstances_id +
                ", tcMuster_id=" + tcMuster_id +
                '}';
    }
}

