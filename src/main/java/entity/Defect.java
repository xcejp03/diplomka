package entity;

import java.sql.Clob;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
public class Defect {
    private String description;
    private List<DefectComment> defectComments;
    private Priority priority;
    private TestCaseInstance testCaseInstance;      //defect může být navázán TC nebo konkrétní step
    private TestStepInstance testStepInstance;      //nebo může být navázáno na oboje
    private TestProject testProject;                // i  na projekt může být navázáno
    private String AffectsVersion;
    private Person Assignee;
    private Person Reporter;
    private DefectStatus defectStatus;
    private Clob file;
}
