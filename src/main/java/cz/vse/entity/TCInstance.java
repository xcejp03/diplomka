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
    @JoinColumn (name = "tcMuster_id")
    private TCMuster tcMuster;

    @ManyToMany (mappedBy = "tcInstances")
    private List<Defect> defects;

    @OneToMany (mappedBy = "tcInstance")
    private List<TSInstance> tsInstances;

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

    public TCMuster getTcMuster() {
        return tcMuster;
    }

    public void setTcMuster(TCMuster tcMuster) {
        this.tcMuster = tcMuster;
    }

    public List<Defect> getDefects() {
        return defects;
    }

    public void setDefects(List<Defect> defects) {
        this.defects = defects;
    }

    public List<TSInstance> getTsInstances() {
        return tsInstances;
    }

    public void setTsInstances(List<TSInstance> tsInstances) {
        this.tsInstances = tsInstances;
    }

    @Override
    public String toString() {
        return "tcInstance{" +
                "name='" + name + '\'' +
//                ", createdDateTime=" + createdDateTime +
//                ", updatedDateTime=" + updatedDateTime +
//                ", tcMuster=" + tcMuster +
//                ", defects=" + defects +
//                ", tsInstances=" + tsInstances +
                '}';
    }
}
