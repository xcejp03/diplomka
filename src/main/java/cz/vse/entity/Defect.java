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
    private PriorityEnum priorityEnum;
    private DefectStatusEnum defectStatusEnum;
    private String AffectsVersion;
//    private Clob file;

    @OneToMany (mappedBy = "id")
    private List<DefectComment> defectComments;

    @ManyToMany    // PROVĚŘIT SPRÁVNOST
    @JoinTable (name = "DEFECT_TCI", joinColumns = @JoinColumn (name = "DEFECT_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "TCI_ID", referencedColumnName = "ID"))
    private List<TestCaseInstance> testCaseInstances;      //defect může být navázán TC nebo konkrétní step


    @ManyToMany    // PROVĚŘIT SPRÁVNOST
    @JoinTable (name = "DEFECT_TSI", joinColumns = @JoinColumn (name = "DEFECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TSI_ID", referencedColumnName = "ID"))
    private List<TestStepInstance> testStepInstances;      //nebo může být navázáno na oboje



    @ManyToOne
    @JoinColumn (name = "assignee_id")
    private Person assignee;

    @ManyToOne
    @JoinColumn (name = "reporter_id")
    private Person reporter;


}
