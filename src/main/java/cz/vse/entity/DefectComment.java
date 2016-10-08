package cz.vse.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class DefectComment extends BaseEntity{
    private LocalDateTime createdDateTime;
    private String commentText;

    @ManyToOne
    @JoinColumn (name = "defect_id")
    private Defect defect;

    @ManyToOne
    @JoinColumn (name = "author_id")
    private Person author;

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

    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "DefectComment{" +
                "createdDateTime=" + createdDateTime +
                ", commentText='" + commentText + '\'' +
                ", defect=" + defect +
                ", author=" + author +
                '}';
    }
}
