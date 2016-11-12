package cz.vse.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TSMuster extends BaseEntity {
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String action;
    private String expected;

    @ManyToMany //(mappedBy = "")
    private List<Defect> defects;

    @OneToMany (mappedBy = "TSMuster")
    private List<TSInstance> TSInstances;

    @ManyToMany (mappedBy = "TSMusters")
    private List<TCMuster> TCMusters;

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
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

    public List<Defect> getDefects() {
        return defects;
    }

    public void setDefects(List<Defect> defects) {
        this.defects = defects;
    }

    public List<TSInstance> getTSInstances() {
        return TSInstances;
    }

    public void setTSInstances(List<TSInstance> TSInstances) {
        this.TSInstances = TSInstances;
    }

    public List<TCMuster> getTCMusters() {
        return TCMusters;
    }

    public void setTCMusters(List<TCMuster> TCMusters) {
        this.TCMusters = TCMusters;
    }

    @Override
    public String toString() {
        return "TSMuster{" +
                "createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", action='" + action + '\'' +
                ", expected='" + expected + '\'' +
                ", defects=" + defects +
                ", TSInstances=" + TSInstances +
                ", TCMusters=" + TCMusters +
                '}';
    }
}
