package cz.vse.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 * <p>
 * Při exekuci se natáhnou stepy vyplěněny jak je napsán bez výsledků testů. Stepy se budou po spuštněí
 * ukládat jako nové instance. V db pak bude - NE! bude to jinak.
 */
@Entity
public class TestCaseInstance extends BaseEntity {
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    @ManyToOne
    @JoinColumn (name = "testCaseMuster_id")
    private TestCaseMuster testCaseMuster;

    @ManyToMany (mappedBy = "testCaseInstances")
    private List<Defect> defects;

    @OneToMany (mappedBy = "testCaseInstance")
    private List<TestStepInstance> testStepInstances;
}
