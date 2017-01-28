package cz.vse.dto;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */
public class ProjectsNamesDTO extends BaseDTO {
    private String name;

    private String projectOwnerName;

    private List<String> suiteIdList;

    private int numberOfTestSuites;

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

    public List<String> getSuiteIdList() {
        return suiteIdList;
    }


    public int getNumberOfTestSuites() {
        return numberOfTestSuites;
    }

    public void setNumberOfTestSuites(int numberOfTestSuites) {
        this.numberOfTestSuites = numberOfTestSuites;
    }

    public void setSuiteIdList(List<String> suiteIdList) {
        this.suiteIdList = suiteIdList;
    }

    @Override
    public String toString() {
        return "ProjectsNamesDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}

