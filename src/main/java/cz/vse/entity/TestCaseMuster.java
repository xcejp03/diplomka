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
public class TestCaseMuster extends BaseEntity {
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;


    @OneToMany (mappedBy = "testCaseMuster")
    private List<TestCaseInstance> testCaseInstances;

    @ManyToOne
    @JoinColumn (name = "testProject_id")
    private TestProject testProject;


    @ManyToMany
    @JoinTable (name = "TCMUSTER_TESTSUITE", joinColumns = @JoinColumn (name = "TCMUSTER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TESTSUITE_ID", referencedColumnName = "ID"))
    private List<TestSuite> testSuites;

    @ManyToMany    // PROVĚŘIT SPRÁVNOST
    @JoinTable (name = "TSMUSTER_TSMUSTER", joinColumns = @JoinColumn (name = "TCMUSTER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TSMUSTER_ID", referencedColumnName = "ID"))
    private List<TestStepMuster> testStepMusters;
}
