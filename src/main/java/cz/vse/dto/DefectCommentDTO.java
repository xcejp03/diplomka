package cz.vse.dto;

/**
 * Created by pcejka on 10.10.2016.
 */
public class DefectCommentDTO extends BaseDTO {

    private String description;
    //    private PriorityEnum priorityEnum;
//    private DefectStatusEnum defectStatusEnum;
    private String AffectsVersion;

    private Long assignee_id;

    private Long reporter_id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAffectsVersion() {
        return AffectsVersion;
    }

    public void setAffectsVersion(String affectsVersion) {
        AffectsVersion = affectsVersion;
    }

    public Long getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(Long assignee_id) {
        this.assignee_id = assignee_id;
    }

    public Long getReporter_id() {
        return reporter_id;
    }

    public void setReporter_id(Long reporter_id) {
        this.reporter_id = reporter_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefectCommentDTO)) return false;
        if (!super.equals(o)) return false;

        DefectCommentDTO that = (DefectCommentDTO) o;

        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getAffectsVersion() != null ? !getAffectsVersion().equals(that.getAffectsVersion()) : that.getAffectsVersion() != null)
            return false;
        if (getAssignee_id() != null ? !getAssignee_id().equals(that.getAssignee_id()) : that.getAssignee_id() != null)
            return false;
        return getReporter_id() != null ? getReporter_id().equals(that.getReporter_id()) : that.getReporter_id() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getAffectsVersion() != null ? getAffectsVersion().hashCode() : 0);
        result = 31 * result + (getAssignee_id() != null ? getAssignee_id().hashCode() : 0);
        result = 31 * result + (getReporter_id() != null ? getReporter_id().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DefectCommentDTO{" +
                "description='" + description + '\'' +
                ", AffectsVersion='" + AffectsVersion + '\'' +
                ", assignee_id=" + assignee_id +
                ", reporter_id=" + reporter_id +
                '}';
    }
}