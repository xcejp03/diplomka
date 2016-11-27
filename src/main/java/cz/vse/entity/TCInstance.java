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
    @JoinColumn(name = "tcMuster_id")
    private TCMuster tcMuster;

    @ManyToMany(mappedBy = "tcInstances")
    private List<Defect> defects;

    @OneToMany(mappedBy = "tcInstance", fetch = FetchType.EAGER)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TCInstance)) return false;

        TCInstance that = (TCInstance) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getCreatedDateTime() != null ? !getCreatedDateTime().equals(that.getCreatedDateTime()) : that.getCreatedDateTime() != null)
            return false;
        if (getUpdatedDateTime() != null ? !getUpdatedDateTime().equals(that.getUpdatedDateTime()) : that.getUpdatedDateTime() != null)
            return false;
        if (getTcMuster() != null ? !getTcMuster().equals(that.getTcMuster()) : that.getTcMuster() != null)
            return false;
        if (getDefects() != null ? !getDefects().equals(that.getDefects()) : that.getDefects() != null) return false;
        return getTsInstances() != null ? getTsInstances().equals(that.getTsInstances()) : that.getTsInstances() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCreatedDateTime() != null ? getCreatedDateTime().hashCode() : 0);
        result = 31 * result + (getUpdatedDateTime() != null ? getUpdatedDateTime().hashCode() : 0);
        result = 31 * result + (getTcMuster() != null ? getTcMuster().hashCode() : 0);
        result = 31 * result + (getDefects() != null ? getDefects().hashCode() : 0);
        result = 31 * result + (getTsInstances() != null ? getTsInstances().hashCode() : 0);
        return result;
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
