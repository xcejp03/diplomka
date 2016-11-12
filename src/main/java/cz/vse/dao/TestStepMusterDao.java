package cz.vse.dao;

import cz.vse.entity.TSMuster;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestStepMusterDao {

    public void saveTestStepMuster(TSMuster TSMuster);
    public void deleteTestStepMuster(TSMuster TSMuster);
    public void updateTestStepMuster(TSMuster TSMuster);
    public List<TSMuster> getAllTestStepMusters();
    public TSMuster getTestStepMusterById(long id);
}
