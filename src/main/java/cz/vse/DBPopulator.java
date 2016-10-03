//package cz.vse;
//
//import cz.vse.dao.*;
//import cz.vse.entity.*;
//import cz.vse.service.PersonService;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.time.LocalDateTime;
//
///**
// * Created by pcejka on 03.10.2016.
// */
//
//@Component
//@Transactional("transactionManager")
//public class DBPopulator {
//    private final Logger l = Logger.getLogger(this.getClass());
//    @PersistenceContext
//    EntityManager em;
//
//    Person person;
//
//    @Autowired
//    private PersonDao personDao;
//    @Autowired
//    private DefectDao defectDao;
//    @Autowired
//    private DefectCommentDao defectCommentDao;
//    @Autowired
//    private TestCaseInstanceDao testCaseInstanceDao;
//    @Autowired
//    private TestCaseMusterDao testCaseMusterDao;
//    @Autowired
//    private TestProjectDao testProjectDao;
//    @Autowired
//    private TestStepInstanceDao testStepInstanceDao;
//    @Autowired
//    private TestStepMusterDao testStepMusterDao;
//    @Autowired
//    private TestSuiteDao testSuiteDao;
//
//    @Autowired
//    private PersonService personService;
//
//    public void populateDatabase() {
//        l.debug("populate database");
//        createPilotPerson();
//        createPilotDefect();
//        createPilotDefectComment();
//        createPilotTestCaseInstance();
//        createPilotTestCaseMuster();
//        createPilotTestProject();
//        createPilotTestStepInstance();
//        createPilotDefectComment();
//        createPilotTestStepMuster();
//        createPilotTestSuit();
//        l.info("population db is complete");
//
//    }
//
//
//    private Person createPilotPerson() {
//        Person person = new Person();
//        person.setName(LocalDateTime.now().toString());
//        person.setCreatedDate(LocalDateTime.now());
//        person.setLogin("sda2");
//        personDao.savePerson(person);
//        return person;
//    }
//
//    private Defect createPilotDefect() {
//        Defect defect = new Defect();
//        defect.setAffectsVersion(LocalDateTime.now().toString());
//        defect.setDescription("založený defekt");
//        defectDao.saveDefect(defect);
//        return defect;
//    }
//
//    private DefectComment createPilotDefectComment() {
//        DefectComment defectComment = new DefectComment();
//        defectComment.setCommentText("Text defektového komentáře");
//        defectComment.setCreatedDateTime(LocalDateTime.now());
//        defectCommentDao.saveDefectComment(defectComment);
//        return defectComment;
//
//    }
//
//    private TestCaseInstance createPilotTestCaseInstance() {
//        TestCaseInstance testCaseInstance = new TestCaseInstance();
//        testCaseInstance.setName(LocalDateTime.now().toString());
//        testCaseInstanceDao.saveTestCaseInstance(testCaseInstance);
//        return testCaseInstance;
//    }
//
//    private TestCaseMuster createPilotTestCaseMuster() {
//        TestCaseMuster testCaseMuster = new TestCaseMuster();
//        testCaseMuster.setName(LocalDateTime.now().toString());
//        testCaseMusterDao.saveTestCaseMuster(testCaseMuster);
//        return testCaseMuster;
//    }
//
//    private TestProject createPilotTestProject() {
//        TestProject testProject = new TestProject();
//        testProject.setName(LocalDateTime.now().toString());
//        testProjectDao.saveTestProject(testProject);
//        return testProject;
//    }
//
//    private TestStepInstance createPilotTestStepInstance() {
//        TestStepInstance testStepInstance = new TestStepInstance();
//        testStepInstance.setAction(LocalDateTime.now().toString());
//        testStepInstance.setActual("Actual behavior of test step instance");
//        testStepInstanceDao.saveTestStepInstance(testStepInstance);
//        return testStepInstance;
//
//    }
//
//    private TestStepMuster createPilotTestStepMuster() {
//        TestStepMuster testStepMuster = new TestStepMuster();
//        testStepMuster.setAction(LocalDateTime.now().toString());
//        testStepMuster.setExpected("Expected behavior of test step muster");
//        testStepMusterDao.saveTestStepMuster(testStepMuster);
//        return testStepMuster;
//
//    }
//
//    private TestSuite createPilotTestSuit() {
//        TestSuite testSuite = new TestSuite();
//        testSuite.setName(LocalDateTime.now().toString());
//        testSuiteDao.saveTestSuite(testSuite);
//        return testSuite;
//
//    }
//
//}
