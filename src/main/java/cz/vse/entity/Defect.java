package cz.vse.entity;

import javax.persistence.*;
import java.sql.Clob;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class Defect extends BaseEntity {
    private String description;

    @OneToMany (mappedBy = "id")
    private List<DefectComment> defectComments;

    private PriorityEnum priorityEnum;

    @ManyToMany //(mappedBy = "id")   // PROVĚŘIT SPRÁVNOST
    private List<TestCaseInstance> testCaseInstances;      //defect může být navázán TC nebo konkrétní step

    @ManyToMany // (mappedBy = "id")
    private List<TestStepInstance> testStepInstances;      //nebo může být navázáno na oboje

    @ManyToMany //(mappedBy = "id")
    private List<TestProject> testProjects;                // i  na projekt může být navázáno

    private String AffectsVersion;

    @ManyToOne
    @JoinColumn (name = "person_id")
    private Person Assignee;
//    private Person Reporter;
    private DefectStatusEnum defectStatusEnum;
//    private Clob file;

    public Defect() {
    }
}
