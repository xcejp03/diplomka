package cz.vse.dto;

import java.time.LocalDateTime;

/**
 * Created by pcejka on 10.10.2016.
 */
public class DefectCommentDTO extends BaseDTO {

    private LocalDateTime createdDateTime;
    private String commentText;

    private Long defect_id;

    private Long author_id;

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getDefect_id() {
        return defect_id;
    }

    public void setDefect_id(Long defect_id) {
        this.defect_id = defect_id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefectCommentDTO)) return false;
        if (!super.equals(o)) return false;

        DefectCommentDTO that = (DefectCommentDTO) o;

        if (getCreatedDateTime() != null ? !getCreatedDateTime().equals(that.getCreatedDateTime()) : that.getCreatedDateTime() != null)
            return false;
        if (getCommentText() != null ? !getCommentText().equals(that.getCommentText()) : that.getCommentText() != null)
            return false;
        if (getDefect_id() != null ? !getDefect_id().equals(that.getDefect_id()) : that.getDefect_id() != null)
            return false;
        return getAuthor_id() != null ? getAuthor_id().equals(that.getAuthor_id()) : that.getAuthor_id() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCreatedDateTime() != null ? getCreatedDateTime().hashCode() : 0);
        result = 31 * result + (getCommentText() != null ? getCommentText().hashCode() : 0);
        result = 31 * result + (getDefect_id() != null ? getDefect_id().hashCode() : 0);
        result = 31 * result + (getAuthor_id() != null ? getAuthor_id().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DefectCommentDTO{" +
                "createdDateTime=" + createdDateTime +
                ", commentText='" + commentText + '\'' +
                ", defect_id=" + defect_id +
                ", author_id=" + author_id +
                '}';
    }
}

