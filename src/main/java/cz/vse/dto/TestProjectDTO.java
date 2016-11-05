package cz.vse.dto;

import cz.vse.entity.Person;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */
public class TestProjectDTO extends BaseDTO {

    private String name;

    private Long projectOwner_id;

    private List<Long> projectMembers_id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestProjectDTO)) return false;
        if (!super.equals(o)) return false;

        TestProjectDTO that = (TestProjectDTO) o;

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
        return "TestProjectDTO{" +
                "name='" + name + '\'' +
                ", projectOwner_id=" + projectOwner_id +
                ", projectMembers=" + projectMembers_id +
                '}';
    }
}

