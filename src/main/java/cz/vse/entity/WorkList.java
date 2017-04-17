package cz.vse.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 * <p>
 * Při exekuci se natáhnou stepy vyplěněny jak je napsán bez výsledků testů. Stepy se budou po spuštněí
 * ukládat jako nové instance. V db pak bude - NE! bude to jinak.
 */
@Entity
public class WorkList extends BaseEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "workList")
    private List<WorkTC> workTCList;

    private LocalDate plannedExecution;

    private PriorityTCEnum priority;

    private String prerequisite;

    private String note;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<WorkTC> getWorkTCList() {
        return workTCList;
    }

    public void setWorkTCList(List<WorkTC> workTCList) {
        this.workTCList = workTCList;
    }

    public LocalDate getPlannedExecution() {
        return plannedExecution;
    }

    public void setPlannedExecution(LocalDate plannedExecution) {
        this.plannedExecution = plannedExecution;
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

    @Override
    public String toString() {
        return "WorkList{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", project=" + project +
                ", plannedExecution=" + plannedExecution +
                ", priority=" + priority +
                ", prerequisite='" + prerequisite + '\'' +
                '}';
    }
}



