package cz.vse.dto;

/**
 * Created by pcejk on 09.04.2017.
 */
public class HelpContext {
    private Long suiteFrom;
    private Long projectId;


    public Long getSuiteFrom() {
        return suiteFrom;
    }

    public void setSuiteFrom(Long suiteFrom) {
        this.suiteFrom = suiteFrom;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
