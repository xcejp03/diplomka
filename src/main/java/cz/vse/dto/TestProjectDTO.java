package cz.vse.dto;

import cz.vse.entity.Person;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */
public class TestProjectDTO extends BaseDTO {

    private String name;

   // private List <Person> projectMembers;

    private Long projectOwner_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public List<Person> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(List<Person> projectMembers) {
        this.projectMembers = projectMembers;
    }
*/

    public Long getProjectOwner_id() {
        return projectOwner_id;
    }

    public void setProjectOwner_id(Long projectOwner_id) {
        this.projectOwner_id = projectOwner_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestProjectDTO)) return false;
        if (!super.equals(o)) return false;

        TestProjectDTO that = (TestProjectDTO) o;

        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestProjectDTO{" +
                "name='" + name + '\'' +
                '}';
    }


}

