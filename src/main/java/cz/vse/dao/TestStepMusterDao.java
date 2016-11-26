package cz.vse.dao;

import cz.vse.entity.TSMuster;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestStepMusterDao {

    void saveTestStepMuster(TSMuster tsMuster);
    void deleteTestStepMuster(Long tsMusterId);
    void updateTestStepMuster(TSMuster tsMuster);
    List<TSMuster> getAllTestStepMusters();
    TSMuster getTestStepMusterById(long id);
    List<TSMuster> getAllTestStepMustersByTCMusterId(long id);

}
