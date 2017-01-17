package cz.vse.dto;

import cz.vse.entity.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 * <p>
 * Při exekuci se natáhnou stepy vyplěněny jak je napsán bez výsledků testů. Stepy se budou po spuštněí
 * ukládat jako nové instance. V db pak bude - NE! bude to jinak.
 */
public class WorkListDTO extends BaseDTO {
    private String name;

    private Long author_id;
//
    private Long project_id;

    private List<WorkTCDTO> workTCList;

    private List<Long> tcMuster_id;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    private String plannedExecution;

    private PriorityTCEnum priority;

    private String prerequisite;

    private String note;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public List<WorkTCDTO> getWorkTCList() {
        return workTCList;
    }

    public void setWorkTCList(List<WorkTCDTO> workTCList) {
        this.workTCList = workTCList;
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

    public String getPlannedExecution() {
        return plannedExecution;
    }

    public void setPlannedExecution(String plannedExecution) {
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

    public List<Long> getTcMuster_id() {
        return tcMuster_id;
    }

    public void setTcMuster_id(List<Long> tcMuster_id) {
        this.tcMuster_id = tcMuster_id;
    }

    @Override
    public String toString() {
        return "WorkListDTO{" +
                "name='" + name + '\'' +
                ", author_id=" + author_id +
                ", project_id=" + project_id +
                ", createdDateTime=" + createdDateTime +
                ", plannedExecution='" + plannedExecution + '\'' +
                ", priority=" + priority +
                ", prerequisite='" + prerequisite + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}



