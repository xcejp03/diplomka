package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 * <p>
 * Při exekuci se natáhnou stepy vyplěněny jak je napsán bez výsledků testů. Stepy se budou po spuštněí
 * ukládat jako nové instance. V db pak bude - NE! bude to jinak.
 */
public class TestCaseMuster {
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private List<TestStepMuster> testStepMusters;
    private List<TestCaseInstance> testCaseInstances;
}
