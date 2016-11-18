package cz.vse.dao.impl;

import cz.vse.dao.TestProjectDao;
import cz.vse.entity.Project;
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
    public void saveTestProject(Project project) {
        l.debug("Saving project: " + project);
        em.persist(project);
        l.info("Project saved successfully. Project detail: " + project);
    }

    @Override
    public void deleteTestProject(Long projectId) {
        l.debug("Deleting project: " + projectId);
        Project project = getTestProjectById(projectId);
        em.remove(project);
        l.info("Project deleted successfully. Project detail: " + projectId);
    }

    @Override
    public void updateTestProject(Project project) {
        l.debug("Updating project: " + project);
        em.merge(project);
        l.info("Project updated successfully. Project detail: " + project);
    }

    @Override
    @Transactional
    public List<Project> getAllTestProjects() {
        l.debug("Getting all project");
        List<Project> resultList = em.createQuery("select d from Project d").getResultList();
        l.info("TestProjects gotten successfully. Project detail: " + resultList.toString());
        return resultList;
    }

    @Override
    public Project getTestProjectById(long id) {
        l.debug("Getting project by id: " + id);
        Project project = em.find(Project.class, id);
        l.info("Gotten project successfully. Project detail: " + project);
        return project;
    }
}
