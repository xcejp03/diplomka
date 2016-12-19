package cz.vse.dto;

/**
 * Created by pcejka on 10.10.2016.
 */
public class ProjectsNamesDTO extends BaseDTO {

    private String name;

    private String projectOwnerName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectOwnerName() {
        return projectOwnerName;
    }

    public void setProjectOwnerName(String projectOwnerName) {
        this.projectOwnerName = projectOwnerName;
    }

    @Override
    public String toString() {
        return "ProjectsNamesDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}

