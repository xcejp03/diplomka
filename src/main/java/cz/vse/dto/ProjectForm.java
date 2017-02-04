package cz.vse.dto;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */
public class ProjectForm extends BaseDTO {

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
    public String toString() {
        return "ProjectForm{" +
                "name='" + name + '\'' +
                ", projectOwner_id=" + projectOwner_id +
                '}';
    }
}

