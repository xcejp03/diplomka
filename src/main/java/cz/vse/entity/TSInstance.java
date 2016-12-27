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
public class TSInstance extends BaseEntity {
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String action;
    private String expected;
    private String result;

    @ManyToOne
    @JoinColumn(name = "tsmuster_id")
    private TSMuster tsMuster;

    @ManyToMany(mappedBy = "tsInstances")
    private List<Defect> defects;

    @ManyToOne
    @JoinColumn(name = "tcinstance_id")
    private TCInstance tCInstance;

    @ManyToOne
    @JoinColumn(name = "tester_id")
    private Person tester;

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

    public Person getTester() {
        return tester;
    }

    public void setTester(Person tester) {
        this.tester = tester;
    }

    @Override
    public String toString() {
        return "TSInstance{" +
//                "createdDateTime=" + createdDateTime +
//                ", updatedDateTime=" + updatedDateTime +
                ", action='" + action + '\'' +
                ", expected='" + expected + '\'' +
                ", actual='" + result + '\'' +
//                ", tsMuster=" + tsMuster +
//                ", defects=" + defects +
//                ", tCInstance=" + tCInstance +
                '}';
    }
}
