package cz.vse.dto.old;

import cz.vse.dto.*;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TCInstanceRunDTO extends cz.vse.dto.BaseDTO {

    private String name;
    private Long tcMusters_id;
    private Long tcInstance_id;
    private List<Long> tsInstances_id;
    private Long tester_id;
    private String prerequisite;
    private String note;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTcMusters_id() {
        return tcMusters_id;
    }

    public void setTcMusters_id(Long tcMusters_id) {
        this.tcMusters_id = tcMusters_id;
    }

    public List<Long> getTsInstances_id() {
        return tsInstances_id;
    }

    public void setTsInstances_id(List<Long> tsInstances_id) {
        this.tsInstances_id = tsInstances_id;
    }

    public Long getTcInstance_id() {
        return tcInstance_id;
    }

    public Long getTester_id() {
        return tester_id;
    }

    public void setTester_id(Long tester_id) {
        this.tester_id = tester_id;
    }

    public void setTcInstance_id(Long tcInstance_id) {
        this.tcInstance_id = tcInstance_id;
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
        return "TCInstanceRunDTO{" +
                "name='" + name + '\'' +
                ", tcMusters_id=" + tcMusters_id +
                ", tcInstance_id=" + tcInstance_id +
                ", tester_id=" + tester_id +
                ", prerequisite='" + prerequisite + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}