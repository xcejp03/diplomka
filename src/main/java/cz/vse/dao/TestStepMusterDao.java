package cz.vse.dao;

import cz.vse.entity.TSMuster;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestStepMusterDao {

    public void saveTestStepMuster(TSMuster tsMuster);
    public void deleteTestStepMuster(Long tsMusterId);
    public void updateTestStepMuster(TSMuster tsMuster);
    public List<TSMuster> getAllTestStepMusters();
    public TSMuster getTestStepMusterById(long id);
}
