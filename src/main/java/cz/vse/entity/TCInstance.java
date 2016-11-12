package cz.vse.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 * <p>
 * Při exekuci se natáhnou stepy vyplěněny jak je napsán bez výsledků testů. Stepy se budou po spuštněí
 * ukládat jako nové instance. V db pak bude - NE! bude to jinak.
 */
@Entity
public class TCInstance extends BaseEntity {
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    @ManyToOne
    @JoinColumn (name = "TCMuster_id")
    private TCMuster TCMuster;

    @ManyToMany (mappedBy = "TCInstances")
    private List<Defect> defects;

    @OneToMany (mappedBy = "TCInstance")
    private List<TSInstance> TSInstances;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public TCMuster getTCMuster() {
        return TCMuster;
    }

    public void setTCMuster(TCMuster TCMuster) {
        this.TCMuster = TCMuster;
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

    @Override
    public String toString() {
        return "TCInstance{" +
                "name='" + name + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", TCMuster=" + TCMuster +
                ", defects=" + defects +
                ", TSInstances=" + TSInstances +
                '}';
    }
}
