package cz.vse.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 11.01.2017.
 */
@Entity
public class WorkTC extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "tcMuster_id")
    private TCMuster tcMuster;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Person assignee;

    @ManyToOne
    @JoinColumn(name = "workList_id")
    private WorkList workList;

    @OneToMany (mappedBy = "workTC")
    private List<TCInstance> tcRunHistory;

    private PriorityTCEnum priority;



    public TCMuster getTcMuster() {
        return tcMuster;
    }

    public void setTcMuster(TCMuster tcMuster) {
        this.tcMuster = tcMuster;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public WorkList getWorkList() {
        return workList;
    }

    public void setWorkList(WorkList workList) {
        this.workList = workList;
    }

    public List<TCInstance> getTcRunHistory() {
        return tcRunHistory;
    }

    public void setTcRunHistory(List<TCInstance> tcRunHistory) {
        this.tcRunHistory = tcRunHistory;
    }

    public PriorityTCEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityTCEnum priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "WorkTC{" +
                "tcMuster=" + tcMuster +
                ", assignee=" + assignee +
                ", workList=" + workList +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WorkTC workTC = (WorkTC) o;

        if (assignee != null ? !assignee.equals(workTC.assignee) : workTC.assignee != null) return false;
        return priority == workTC.priority;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (assignee != null ? assignee.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }
}
