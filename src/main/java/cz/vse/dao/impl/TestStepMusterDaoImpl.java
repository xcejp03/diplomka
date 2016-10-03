package cz.vse.dao.impl;

import cz.vse.dao.TestStepMusterDao;
import cz.vse.entity.TestStepMuster;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class TestStepMusterDaoImpl implements TestStepMusterDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @Override
    public void addTestStepMuster(TestStepMuster testStepMuster) {

    }

    @Override
    public void deleteTestStepMuster(TestStepMuster testStepMuster) {

    }

    @Override
    public void updateTestStepMuster(TestStepMuster testStepMuster) {

    }

    @Override
    public List<TestStepMuster> getAllTestStepMuster() {
        return null;
    }

    @Override
    public TestStepMuster getTestStepMusterById(long id) {
        return null;
    }
}
