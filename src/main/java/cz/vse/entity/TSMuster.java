package cz.vse.entity;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;

    @ManyToMany //(mappedBy = "")
    private List<Defect> defects;

    @OneToMany(mappedBy = "tsMuster")
    private List<TSInstance> tsInstances;

    @ManyToMany(mappedBy = "tsMusters")
    private List<TCMuster> tcMusters;

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

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
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

    public List<TCMuster> getTcMusters() {
        return tcMusters;
    }

    public void setTcMusters(List<TCMuster> tcMusters) {
        this.tcMusters = tcMusters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TSMuster)) return false;

        TSMuster tsMuster = (TSMuster) o;

        if (getCreatedDateTime() != null ? !getCreatedDateTime().equals(tsMuster.getCreatedDateTime()) : tsMuster.getCreatedDateTime() != null)
            return false;
        if (getUpdatedDateTime() != null ? !getUpdatedDateTime().equals(tsMuster.getUpdatedDateTime()) : tsMuster.getUpdatedDateTime() != null)
            return false;
        if (getAction() != null ? !getAction().equals(tsMuster.getAction()) : tsMuster.getAction() != null)
            return false;
        if (getExpected() != null ? !getExpected().equals(tsMuster.getExpected()) : tsMuster.getExpected() != null)
            return false;
        if (getAuthor() != null ? !getAuthor().equals(tsMuster.getAuthor()) : tsMuster.getAuthor() != null)
            return false;
        if (getDefects() != null ? !getDefects().equals(tsMuster.getDefects()) : tsMuster.getDefects() != null)
            return false;
        if (getTsInstances() != null ? !getTsInstances().equals(tsMuster.getTsInstances()) : tsMuster.getTsInstances() != null)
            return false;
        return getTcMusters() != null ? getTcMusters().equals(tsMuster.getTcMusters()) : tsMuster.getTcMusters() == null;

    }

    @Override
    public int hashCode() {
        int result = getCreatedDateTime() != null ? getCreatedDateTime().hashCode() : 0;
        result = 31 * result + (getUpdatedDateTime() != null ? getUpdatedDateTime().hashCode() : 0);
        result = 31 * result + (getAction() != null ? getAction().hashCode() : 0);
        result = 31 * result + (getExpected() != null ? getExpected().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getDefects() != null ? getDefects().hashCode() : 0);
        result = 31 * result + (getTsInstances() != null ? getTsInstances().hashCode() : 0);
        result = 31 * result + (getTcMusters() != null ? getTcMusters().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TSMuster{" +
                "createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", action='" + action + '\'' +
                ", expected='" + expected + '\'' +
                ", author=" + author +
                ", defects=" + defects +
                ", tsInstances=" + tsInstances +
                ", tcMusters=" + tcMusters +
                '}';
    }
}