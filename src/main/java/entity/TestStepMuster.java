package entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
public class TestStepMuster {
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String action;
    private String expected;
    private List<Defect> defects;
    private List<TestStepInstance> testStepInstances;
}
