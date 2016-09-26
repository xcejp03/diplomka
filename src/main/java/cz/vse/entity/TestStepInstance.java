package cz.vse.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TestStepInstance  extends BaseEntity{
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String action;
    private String expected;
    private String actual;

    @ManyToMany
    private List<Defect> defects;

    @ManyToOne
    @JoinColumn (name = "testStepMuster_id")
    private TestStepMuster testStepMuster;

}
