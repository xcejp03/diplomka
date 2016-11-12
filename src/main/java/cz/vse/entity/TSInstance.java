package cz.vse.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TSInstance extends BaseEntity{
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String action;
    private String expected;
    private String actual;

    @ManyToOne
    @JoinColumn (name = "TSMuster_id")
    private TSMuster TSMuster;

    @ManyToMany (mappedBy = "TSInstances")
    private List<Defect> defects;

    @ManyToOne
    @JoinColumn (name = "TCInstance_id")
    private TCInstance TCInstance;

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

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public TSMuster getTSMuster() {
        return TSMuster;
    }

    public void setTSMuster(TSMuster TSMuster) {
        this.TSMuster = TSMuster;
    }

    public List<Defect> getDefects() {
        return defects;
    }

    public void setDefects(List<Defect> defects) {
        this.defects = defects;
    }

    public TCInstance getTCInstance() {
        return TCInstance;
    }

    public void setTCInstance(TCInstance TCInstance) {
        this.TCInstance = TCInstance;
    }

    @Override
    public String toString() {
        return "TSInstance{" +
                "createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", action='" + action + '\'' +
                ", expected='" + expected + '\'' +
                ", actual='" + actual + '\'' +
                ", TSMuster=" + TSMuster +
                ", defects=" + defects +
                ", TCInstance=" + TCInstance +
                '}';
    }
}
