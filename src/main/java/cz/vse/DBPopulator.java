package cz.vse;

import cz.vse.dao.*;
import cz.vse.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

/**
 * Created by pcejka on 03.10.2016.
 */

@Component
@Transactional("transactionManager")
public class DBPopulator {
    private final Logger l = Logger.getLogger(this.getClass());
    @PersistenceContext
    EntityManager em;

    @Autowired
    private PersonDao personDao;
    @Autowired
    private DefectDao defectDao;
    @Autowired
    private DefectCommentDao defectCommentDao;
    @Autowired
    private TestCaseInstanceDao testCaseInstanceDao;
    @Autowired
    private TestCaseMusterDao testCaseMusterDao;
    @Autowired
    private TestProjectDao testProjectDao;
    @Autowired
    private TestStepInstanceDao testStepInstanceDao;
    @Autowired
    private TestStepMusterDao testStepMusterDao;
    @Autowired
    private TestSuiteDao testSuiteDao;

    public void populateDatabase() {
        l.debug("populate database");
        createPilotDefect();
        createPilotDefectComment();
        createPilotPerson();
        createPilotTestCaseInstance();
        createPilotTestCaseMuster();
        createPilotTestProject();
        createPilotTestStepInstance();
        createPilotDefectComment();
        createPilotTestStepMuster();
        createPilotTestSuit();

    }


    private Person createPilotPerson() {
        Person person = new Person();
        person.setName(LocalDateTime.now().toString());
        person.setCreatedDate(LocalDateTime.now());
        person.setLogin("sda2");
        personDao.addPerson(person);
        return person;
    }

    private Defect createPilotDefect() {
        Defect defect = new Defect();
        defect.setAffectsVersion(LocalDateTime.now().toString());
        defect.setDescription("založený defekt");
        defectDao.addDefect(defect);
        return defect;
    }

    private DefectComment createPilotDefectComment() {
        DefectComment defectComment = new DefectComment();
        defectComment.setCommentText("Text defektového komentáře");
        defectComment.setCreatedDateTime(LocalDateTime.now());
        defectCommentDao.addDefectComment(defectComment);
        return defectComment;

    }

    private TestCaseInstance createPilotTestCaseInstance() {
        TestCaseInstance testCaseInstance = new TestCaseInstance();
        testCaseInstance.setName(LocalDateTime.now().toString());
        testCaseInstanceDao.addTestCaseInstance(testCaseInstance);
        return testCaseInstance;
    }

    private TestCaseMuster createPilotTestCaseMuster() {
        TestCaseMuster testCaseMuster = new TestCaseMuster();
        testCaseMuster.setName(LocalDateTime.now().toString());
        testCaseMusterDao.addTestCaseMuster(testCaseMuster);
        return testCaseMuster;
    }

    private TestProject createPilotTestProject() {
        TestProject testProject = new TestProject();
        testProject.setName(LocalDateTime.now().toString());
        testProjectDao.addTestProject(testProject);
        return testProject;
    }

    private TestStepInstance createPilotTestStepInstance() {
        TestStepInstance testStepInstance = new TestStepInstance();
        testStepInstance.setAction(LocalDateTime.now().toString());
        testStepInstance.setActual("Actual behavior of test step instance");
        testStepInstanceDao.addTestStepInstance(testStepInstance);
        return testStepInstance;

    }

    private TestStepMuster createPilotTestStepMuster() {
        TestStepMuster testStepMuster = new TestStepMuster();
        testStepMuster.setAction(LocalDateTime.now().toString());
        testStepMuster.setExpected("Expected behavior of test step muster");
        testStepMusterDao.addTestStepMuster(testStepMuster);
        return testStepMuster;

    }

    private TestSuite createPilotTestSuit() {
        TestSuite testSuite = new TestSuite();
        testSuite.setName(LocalDateTime.now().toString());
        testSuiteDao.addTestSuite(testSuite);
        return testSuite;

    }

}
