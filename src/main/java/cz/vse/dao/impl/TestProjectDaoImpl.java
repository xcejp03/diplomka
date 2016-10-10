package cz.vse.dao.impl;

import cz.vse.dao.TestProjectDao;
import cz.vse.entity.TestProject;
import cz.vse.entity.TestProject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
@Transactional
public class TestProjectDaoImpl implements TestProjectDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;
    @Override
    public void saveTestProject(TestProject testProject) {
        l.debug("Saving testProject: " + testProject);
        em.persist(testProject);
        l.info("TestProject saved successfully. TestProject detail: " + testProject);
    }

    @Override
    public void deleteTestProject(TestProject testProject) {
        l.debug("Deleting testProject: " + testProject);
        em.remove(testProject);
        l.info("TestProject deleted successfully. TestProject detail: " + testProject);
    }

    @Override
    public void updateTestProject(TestProject testProject) {
        l.debug("Updating testProject: " + testProject);
        em.merge(testProject);
        l.info("TestProject updated successfully. TestProject detail: " + testProject);
    }

    @Override
    @Transactional
    public List<TestProject> getAllTestProjects() {
        l.debug("Getting all testProject");
        List<TestProject> resultList = em.createQuery("select d from TestProject d").getResultList();
        l.info("TestProjects gotten successfully. TestProject detail: " + resultList.toString());
        return resultList;
    }

    @Override
    public TestProject getTestProjectById(long id) {
        l.debug("Getting testProject by id: " + id);
        TestProject testProject = em.find(TestProject.class, id);
        l.info("Gotten testProject successfully. TestProject detail: " + testProject);
        return testProject;
    }
}
