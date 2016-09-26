package cz.vse.entity;

import javax.persistence.Entity;
import java.sql.Clob;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class Defect extends BaseEntity {
    private String description;
    private List<DefectComment> defectComments;
    private PriorityEnum priorityEnum;
    private TestCaseInstance testCaseInstance;      //defect může být navázán TC nebo konkrétní step
    private TestStepInstance testStepInstance;      //nebo může být navázáno na oboje
    private TestProject testProject;                // i  na projekt může být navázáno
    private String AffectsVersion;
    private Person Assignee;
//    private Person Reporter;
    private DefectStatusEnum defectStatusEnum;
    private Clob file;

    public Defect() {
    }
}
