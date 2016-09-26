package cz.vse.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TestStepMuster extends BaseEntity {
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String action;
    private String expected;

    @ManyToMany //(mappedBy = "")
    private List<Defect> defects;

    @OneToMany (mappedBy = "testStepMuster")
    private List<TestStepInstance> testStepInstances;

    @ManyToMany (mappedBy = "testStepMusters")
    private List<TestCaseMuster> testCaseMusters;
}
