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
public class TestSuite extends BaseEntity {
    private String name;        //testsuit je možné si pojmenovat
    private LocalDateTime createdDateTime;          // datum vytvoření testsuitu;
    private LocalDateTime updateDateTime;

    @ManyToOne
    @JoinColumn (name = "testProject_id")
    private TestProject testProject;        //testsuit patří pod jeden projekt

    @ManyToMany // (mappedBy = "id")
    private List<TestCaseMuster> testCaseMusters;      //testsuit se skládá z testcasů;
}
