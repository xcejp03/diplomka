package cz.vse.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TSInstance extends BaseEntity {
    private String action;
    private String expected;
    private String result;
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "tsmuster_id")
    private TSMuster tsMuster;

    @ManyToMany(mappedBy = "tsInstances")
    private List<Defect> defects;

    @ManyToOne
    @JoinColumn(name = "tcinstance_id")
    private TCInstance tCInstance;

    @ManyToOne
    @JoinColumn(name = "testerUpdate_id")
    private Person testerUpdate;

    @OneToMany(mappedBy = "tsInstanceSource")
    private List<Defect> defectList;

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

    public void setResult(String actual) {
        this.result = actual;
    }

    public TSMuster getTsMuster() {
        return tsMuster;
    }

    public void setTsMuster(TSMuster TSMuster) {
        this.tsMuster = TSMuster;
    }

    public List<Defect> getDefects() {
        return defects;
    }

    public void setDefects(List<Defect> defects) {
        this.defects = defects;
    }

    public TCInstance gettCInstance() {
        return tCInstance;
    }

    public void settCInstance(TCInstance tCInstance) {
        this.tCInstance = tCInstance;
    }


    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<Defect> getDefectList() {
        return defectList;
    }

    public void setDefectList(List<Defect> defectList) {
        this.defectList = defectList;
    }

    public Person getTesterUpdate() {
        return testerUpdate;
    }

    public void setTesterUpdate(Person testerUpdate) {
        this.testerUpdate = testerUpdate;
    }

    @Override
    public String toString() {
        return "TSInstance{" +
                "action='" + action + '\'' +
                ", expected='" + expected + '\'' +
                ", result='" + result + '\'' +
                ", status=" + status +
                '}';
    }
}
