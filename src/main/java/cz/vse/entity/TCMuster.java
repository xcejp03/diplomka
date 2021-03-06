package cz.vse.entity;

import javax.persistence.*;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Person Author;

    private PriorityTCEnum priority;

    private String prerequisite;

    private String note;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "workTC_id")
    private WorkTC workTC;

    @OneToMany(mappedBy = "tCMuster", fetch = FetchType.EAGER)
    private List<TCInstance> tcInstances;

    @ManyToMany
    @JoinTable(name = "TCMUSTER_TESTSUITE", joinColumns = @JoinColumn(name = "TCMUSTER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TESTSUITE_ID", referencedColumnName = "ID"))
    private List<TestSuite> testSuites;

    @OneToMany(mappedBy = "tCMuster", fetch = FetchType.EAGER)
    private List<TSMuster> tsMusters;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getAuthor() {
        return Author;
    }

    public void setAuthor(Person author) {
        Author = author;
    }

    public PriorityTCEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityTCEnum priority) {
        this.priority = priority;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public WorkTC getWorkTC() {
        return workTC;
    }

    public void setWorkTC(WorkTC workTC) {
        this.workTC = workTC;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    public void addTestSuites(TestSuite testSuite) {
        this.testSuites.add(testSuite);
    }

    public List<TSMuster> getTsMusters() {
        return tsMusters;
    }

    public void setTsMusters(List<TSMuster> tsMusters) {
        this.tsMusters = tsMusters;
    }

    @Override
    public String toString() {
        return "TCMuster{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", prerequisite='" + prerequisite + '\'' +
                ", project=" + project +
                '}';
    }

    public List<TCInstance> getTcInstances() {
        return tcInstances;
    }

    public void setTcInstances(List<TCInstance> tcInstances) {
        this.tcInstances = tcInstances;
    }


}
