package entity;

import java.time.LocalDateTime;

/**
 * Created by pcejka on 21.09.2016.
 */
public class TestSuite {
    private String name;        //testsuit je možné si pojmenovat
    private LocalDateTime createdDateTime;          // datum vytvoření testsuitu;
    private LocalDateTime updateDateTime;
    private TestProject testProject;        //testsuit patří pod jeden projekt
    private TestCaseInstance testCaseInstance;      //testsuit se skládá z testcasů;
}
