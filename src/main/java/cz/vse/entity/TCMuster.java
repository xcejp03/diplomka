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
public class TCMuster extends BaseEntity {
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;


    @OneToMany (mappedBy = "TCMuster")
    private List<TCInstance> TCInstances;

    @ManyToOne
    @JoinColumn (name = "project_id")
    private Project project;


    @ManyToMany
    @JoinTable (name = "TCMUSTER_TESTSUITE", joinColumns = @JoinColumn (name = "TCMUSTER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TESTSUITE_ID", referencedColumnName = "ID"))
    private List<TestSuite> testSuites;

    @ManyToMany    // PROVĚŘIT SPRÁVNOST
    @JoinTable (name = "TSMUSTER_TSMUSTER", joinColumns = @JoinColumn (name = "TCMUSTER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TSMUSTER_ID", referencedColumnName = "ID"))
    private List<TSMuster> TSMusters;

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

    public List<TCInstance> getTCInstances() {
        return TCInstances;
    }

    public void setTCInstances(List<TCInstance> TCInstances) {
        this.TCInstances = TCInstances;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    public List<TSMuster> getTSMusters() {
        return TSMusters;
    }

    public void setTSMusters(List<TSMuster> TSMusters) {
        this.TSMusters = TSMusters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TCMuster)) return false;

        TCMuster tcMuster = (TCMuster) o;

        if (getName() != null ? !getName().equals(tcMuster.getName()) : tcMuster.getName() != null) return false;
        if (getCreatedDateTime() != null ? !getCreatedDateTime().equals(tcMuster.getCreatedDateTime()) : tcMuster.getCreatedDateTime() != null)
            return false;
        if (getUpdatedDateTime() != null ? !getUpdatedDateTime().equals(tcMuster.getUpdatedDateTime()) : tcMuster.getUpdatedDateTime() != null)
            return false;
        if (getTCInstances() != null ? !getTCInstances().equals(tcMuster.getTCInstances()) : tcMuster.getTCInstances() != null)
            return false;
        if (getProject() != null ? !getProject().equals(tcMuster.getProject()) : tcMuster.getProject() != null)
            return false;
        if (getTestSuites() != null ? !getTestSuites().equals(tcMuster.getTestSuites()) : tcMuster.getTestSuites() != null)
            return false;
        return getTSMusters() != null ? getTSMusters().equals(tcMuster.getTSMusters()) : tcMuster.getTSMusters() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCreatedDateTime() != null ? getCreatedDateTime().hashCode() : 0);
        result = 31 * result + (getUpdatedDateTime() != null ? getUpdatedDateTime().hashCode() : 0);
        result = 31 * result + (getTCInstances() != null ? getTCInstances().hashCode() : 0);
        result = 31 * result + (getProject() != null ? getProject().hashCode() : 0);
        result = 31 * result + (getTestSuites() != null ? getTestSuites().hashCode() : 0);
        result = 31 * result + (getTSMusters() != null ? getTSMusters().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TCMuster{" +
                "name='" + name + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", TCInstances=" + TCInstances +
                ", project=" + project +
                ", testSuites=" + testSuites +
                ", TSMusters=" + TSMusters +
                '}';
    }
}
