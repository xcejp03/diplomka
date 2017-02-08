package cz.vse.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by pcejka on 19.11).2016.
 */
public class SuiteForm extends BaseDTO {
    @Size(min = 1, max = 50)
    private String name;
    private String createdDateTime;
    private String updateDateTime;
    @NotNull
    private Long project_id;
    private List<Long> tcMusters_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public List<Long> getTcMusters_id() {
        return tcMusters_id;
    }

    public void setTcMusters_id(List<Long> tcMusters_id) {
        this.tcMusters_id = tcMusters_id;
    }

    @Override
    public String toString() {
        return "SuiteDTO{" +
                "name='" + name + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updateDateTime=" + updateDateTime +
                ", project_id=" + project_id +
                ", tcMusters_id=" + tcMusters_id +
                '}';
    }
}
