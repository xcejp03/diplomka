package cz.vse.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class DefectComment extends BaseEntity{
    private LocalDateTime createdDateTime;
//    private Person author;
    private String commentText;
}
