package cz.vse.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 19.11).2016.
 */
public class TestSuiteDTO extends BaseDTO{
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime updateDateTime;
    private Long project_id;
    private List<Long> tcMusters_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
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
        return "TestSuiteDTO{" +
                "name='" + name + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", updateDateTime=" + updateDateTime +
                ", project_id=" + project_id +
                ", tcMusters_id=" + tcMusters_id +
                '}';
    }
}
