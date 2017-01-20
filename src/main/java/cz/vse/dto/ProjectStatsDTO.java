package cz.vse.dto;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */
public class ProjectStatsDTO extends BaseDTO {

    private String name;
    private String projectOwner_name;
    private int numberOfTCs;
    private int numberOfPassedTCs;
    private int numberOfFailedTCs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectOwner_name() {
        return projectOwner_name;
    }

    public void setProjectOwner_name(String projectOwner_name) {
        this.projectOwner_name = projectOwner_name;
    }

    public int getNumberOfTCs() {
        return numberOfTCs;
    }

    public void setNumberOfTCs(int numberOfTCs) {
        this.numberOfTCs = numberOfTCs;
    }

    public int getNumberOfPassedTCs() {
        return numberOfPassedTCs;
    }

    public void setNumberOfPassedTCs(int numberOfPassedTCs) {
        this.numberOfPassedTCs = numberOfPassedTCs;
    }

    public int getNumberOfFailedTCs() {
        return numberOfFailedTCs;
    }

    public void setNumberOfFailedTCs(int numberOfFailedTCs) {
        this.numberOfFailedTCs = numberOfFailedTCs;
    }

    @Override
    public String toString() {
        return "ProjectStatsDTO{" +
                "name='" + name + '\'' +
                ", projectOwner_name='" + projectOwner_name + '\'' +
                ", numberOfTCs=" + numberOfTCs +
                ", numberOfPassedTCs=" + numberOfPassedTCs +
                ", numberOfFailedTCs=" + numberOfFailedTCs +
                '}';
    }
}

