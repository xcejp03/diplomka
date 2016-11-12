package cz.vse.dao;

import cz.vse.entity.TCMuster;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TestCaseMusterDao {

    public void saveTestCaseMuster(TCMuster TCMuster);
    public void deleteTestCaseMuster(TCMuster TCMuster);
    public void updateTestCaseMuster(TCMuster TCMuster);
    public List<TCMuster> getAllTestCaseMusters();
    public TCMuster getTestCaseMusterById(long id);
}
