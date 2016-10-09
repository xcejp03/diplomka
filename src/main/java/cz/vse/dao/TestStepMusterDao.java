package cz.vse.dao;

import cz.vse.entity.TestStepMuster;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestStepMusterDao {

    public void saveTestStepMuster(TestStepMuster testStepMuster);
    public void deleteTestStepMuster(TestStepMuster testStepMuster);
    public void updateTestStepMuster(TestStepMuster testStepMuster);
    public List<TestStepMuster> getAllTestStepMusters();
    public TestStepMuster getTestStepMusterById(long id);
}
