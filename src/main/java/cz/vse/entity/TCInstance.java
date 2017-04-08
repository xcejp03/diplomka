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
    private String prerequisite;
    private String note;
    private StatusEnum status;
    private String backId;
    private String instanceOrigin;

    @ManyToOne
    @JoinColumn(name = "tcMuster_id")
    private TCMuster tCMuster;

    @ManyToMany(mappedBy = "tcInstances")
    private List<Defect> defects;

    @OneToMany(mappedBy = "tCInstance")//, fetch = FetchType.EAGER)
    private List<TSInstance> tsInstances;

    @ManyToOne
    @JoinColumn(name = "tester_id")
    private Person tester;

    @OneToMany(mappedBy = "tsInstanceSource")
    private List<Defect> defectList;

    @ManyToOne
    @JoinColumn(name = "workTCId")
    private WorkTC workTC;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TCMuster gettCMuster() {
        return tCMuster;
    }

    public void settCMuster(TCMuster tCMuster) {
        this.tCMuster = tCMuster;
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

    public Person getTester() {
        return tester;
    }

    public void setTester(Person tester) {
        this.tester = tester;
    }

    public List<Defect> getDefectList() {
        return defectList;
    }

    public void setDefectList(List<Defect> defectList) {
        this.defectList = defectList;
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

    public WorkTC getWorkTC() {
        return workTC;
    }

    public void setWorkTC(WorkTC workTC) {
        this.workTC = workTC;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }


    public String getBackId() {
        return backId;
    }

    public void setBackId(String backId) {
        this.backId = backId;
    }

    public String getInstanceOrigin() {
        return instanceOrigin;
    }

    public void setInstanceOrigin(String instanceOrigin) {
        this.instanceOrigin = instanceOrigin;
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
        if (gettCMuster() != null ? !gettCMuster().equals(that.gettCMuster()) : that.gettCMuster() != null)
            return false;
        if (getDefects() != null ? !getDefects().equals(that.getDefects()) : that.getDefects() != null) return false;
        return getTsInstances() != null ? getTsInstances().equals(that.getTsInstances()) : that.getTsInstances() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCreatedDateTime() != null ? getCreatedDateTime().hashCode() : 0);
        result = 31 * result + (getUpdatedDateTime() != null ? getUpdatedDateTime().hashCode() : 0);
        result = 31 * result + (gettCMuster() != null ? gettCMuster().hashCode() : 0);
        result = 31 * result + (getDefects() != null ? getDefects().hashCode() : 0);
        result = 31 * result + (getTsInstances() != null ? getTsInstances().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TCInstance - " + id + " {" +
                "name='" + name + '\'' +
                ", prerequisite='" + prerequisite + '\'' +
                ", note='" + note + '\'' +
                ", tCMuster=" + tCMuster +
                '}';
    }
}
