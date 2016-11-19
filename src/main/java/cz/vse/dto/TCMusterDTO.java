package cz.vse.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TCMusterDTO extends BaseDTO {

    private LocalDateTime createdDateTime;
    private String name;
    private Long project_id;

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TCMusterDTO)) return false;
        if (!super.equals(o)) return false;

        TCMusterDTO that = (TCMusterDTO) o;

        if (getCreatedDateTime() != null ? !getCreatedDateTime().equals(that.getCreatedDateTime()) : that.getCreatedDateTime() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getProject_id() != null ? getProject_id().equals(that.getProject_id()) : that.getProject_id() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCreatedDateTime() != null ? getCreatedDateTime().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getProject_id() != null ? getProject_id().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TCMusterDTO{" +
                "createdDateTime=" + createdDateTime +
                ", name='" + name + '\'' +
                ", project_id=" + project_id +
                '}';
    }
}

