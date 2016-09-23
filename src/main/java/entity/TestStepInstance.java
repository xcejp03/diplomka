package entity;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TestStepInstance  extends BaseEntity{
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String action;
    private String expected;
    private String actual;
    private List<Defect> defects;
    private TestStepMuster testStepMuster;

}
