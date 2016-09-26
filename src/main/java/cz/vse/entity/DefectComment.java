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
    @JoinColumn (name = "author_id")
    private Person author;


}
