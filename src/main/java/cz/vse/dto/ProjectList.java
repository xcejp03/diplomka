package cz.vse.dto;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */
public class ProjectList extends BaseDTO {

    private String name;
    private Long projectOwner_id;
    private List<Long> projectMembers_id;
    private List<Long> tcMusters_id;
    private List<Long> suites_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectOwner_id() {
        return projectOwner_id;
    }

    public void setProjectOwner_id(Long projectOwner_id) {
        this.projectOwner_id = projectOwner_id;
    }

    public List<Long> getProjectMembers_id() {
        return projectMembers_id;
    }

    public void setProjectMembers_id(List<Long> projectMembers_id) {
        this.projectMembers_id = projectMembers_id;
    }

    public List<Long> getTcMusters_id() {
        return tcMusters_id;
    }

    public void setTcMusters_id(List<Long> tcMusters_id) {
        this.tcMusters_id = tcMusters_id;
    }

    public List<Long> getSuites_id() {
        return suites_id;
    }

    public void setSuites_id(List<Long> suites_id) {
        this.suites_id = suites_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectList)) return false;
        if (!super.equals(o)) return false;

        ProjectList that = (ProjectList) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getProjectOwner_id() != null ? !getProjectOwner_id().equals(that.getProjectOwner_id()) : that.getProjectOwner_id() != null)
            return false;
        return getProjectMembers_id() != null ? getProjectMembers_id().equals(that.getProjectMembers_id()) : that.getProjectMembers_id() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getProjectOwner_id() != null ? getProjectOwner_id().hashCode() : 0);
        result = 31 * result + (getProjectMembers_id() != null ? getProjectMembers_id().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "name='" + name + '\'' +
                ", projectOwner_id=" + projectOwner_id +
                ", projectMembers=" + projectMembers_id +
                '}';
    }
}

