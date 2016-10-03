package cz.vse.dao.impl;

import cz.vse.dao.TestProjectDao;
import cz.vse.entity.TestProject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class TestProjectDaoImpl implements TestProjectDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @Override
    public void addTestProject(TestProject testProject) {

    }

    @Override
    public void deleteTestProject(TestProject testProject) {

    }

    @Override
    public void updateTestProject(TestProject testProject) {

    }

    @Override
    public List<TestProject> getAllTestProject() {
        return null;
    }

    @Override
    public TestProject getTestProjectById(long id) {
        return null;
    }
}
