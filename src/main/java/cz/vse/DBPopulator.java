package cz.vse;

import cz.vse.dao.*;
import cz.vse.entity.*;
import cz.vse.service.DefectCommentService;
import cz.vse.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */

@Component
@Transactional("transactionManager")
public class DBPopulator {
    private final Logger l = Logger.getLogger(this.getClass());
//    @PersistenceContext (unitName = "tutorialUnit")
//    EntityManager em;
//    Person person;

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

    @Autowired
    private PersonService personService;

    @Autowired
    private DefectCommentService defectCommentService;

    public void populateDatabase() {
        l.debug("populate database");
//        createAllDatasWithConstraints();
        createPilotPerson();
        createPilotDefect();
        createPilotDefectComment();
        createPilotTestCaseInstance();
        createPilotTestCaseMuster();
        createPilotTestProject();
        createPilotTestStepInstance();
        createPilotDefectComment();
        createPilotTestStepMuster();
        createPilotTestSuit();
        l.info("population db is complete");
//        l.info("print db constraint data");
//        defectDao.getAllDefects();
    }

    public void createAllDatasWithConstraints() {
        Person person = new Person();
        person.setName(LocalDateTime.now().toString());
        person.setCreatedDate(LocalDateTime.now());
        person.setLogin("login osoby");
        Person personAssigne = new Person();
        personAssigne.setName("Assognee");
        Person personAuthor = new Person();
        personAuthor.setName("Defect author");

        //  DEFECT
        Defect defect = new Defect();
        defect.setAffectsVersion(LocalDateTime.now().toString());
        defect.setDescription("založený defekt");
        defect.setAssignee(personAssigne);
        defect.setReporter(personAuthor);

        //DEFECT COMMENT
        DefectComment defectComment = new DefectComment();
        defectComment.setCommentText("Text defektového komentáře");
        defectComment.setCreatedDateTime(LocalDateTime.now());
        defectComment.setAuthor(person);
        defectComment.setDefect(defect);

        //TEST PROJECT
        Project project = new Project();
        project.setName(LocalDateTime.now().toString());
        List<Person> projectPersonList = new ArrayList<>();
        projectPersonList.add(personAuthor);
        project.setPersonMembers(projectPersonList);
        project.setProjectOwner(personAssigne);

        //TEST SUITE
        TestSuite testSuite = new TestSuite();
        testSuite.setName(LocalDateTime.now().toString());
        testSuite.setProject(project);
        List<TestSuite> testSuites = new ArrayList<>();

        //TEST CASE MUSTER
        TCMuster TCMuster = new TCMuster();
        TCMuster.setName(LocalDateTime.now().toString());
        testSuites.add(testSuite);
        TCMuster.setTestSuites(testSuites);
        TCMuster.setProject(project);

        //TEST STEP MUSTER
        TSMuster TSMuster = new TSMuster();
        TSMuster.setAction(LocalDateTime.now().toString());
        TSMuster.setExpected("Expected behavior of test step muster");

        //TEST CASE INSTANCE
        TCInstance TCInstance = new TCInstance();
        TCInstance.setName(LocalDateTime.now().toString());
        TCInstance.setTcMuster(TCMuster);

        //TEST STEP INSTANCE
        TSInstance TSInstance = new TSInstance();
        TSInstance.setAction(LocalDateTime.now().toString());
        TSInstance.setResult("Actual behavior of test step instance");
        TSInstance.setTsMuster(TSMuster);
        TSInstance.setTcInstance(TCInstance);
        List<Defect> stepInstanceDefectList = new ArrayList<>();
        stepInstanceDefectList.add(defect);
        TSInstance.setDefects(stepInstanceDefectList);

        personDao.savePerson(personAssigne);
        personDao.savePerson(personAuthor);
        testProjectDao.saveTestProject(project);
        testSuiteDao.saveTestSuite(testSuite);
        personDao.savePerson(person);
        testCaseMusterDao.saveTestCaseMuster(TCMuster);
        testStepMusterDao.saveTestStepMuster(TSMuster);
        testCaseInstanceDao.saveTestCaseInstance(TCInstance);
        defectDao.saveDefect(defect);
        defectCommentDao.saveDefectComment(defectComment);
        testStepInstanceDao.saveTestStepInstance(TSInstance);
        l.debug("naplnění db hotovo");
        l.debug("test service pro defect comment");
        defectCommentService.createComment(defect, personAssigne, "Text u defektu");
        l.error(defectCommentService.findAllDefectsComments(defect).toString());
        defectComment.setCommentText("upravený text v čase: "+LocalDateTime.now().toString());
        defectCommentService.updateComment(defectComment);
        l.error(defectCommentService.findAllDefectsComments(defect).toString());

    }

    private Person createPilotPerson() {
        Person person = new Person();
        person.setName(LocalDateTime.now().toString());
        person.setCreatedDate(LocalDateTime.now());
        person.setLogin("login osoby");

        personDao.savePerson(person);
        return person;
    }

    private Defect createPilotDefect() {
        Defect defect = new Defect();
        defect.setAffectsVersion(LocalDateTime.now().toString());
        defect.setDescription("založený defekt");
        defectDao.saveDefect(defect);
        return defect;
    }

    private DefectComment createPilotDefectComment() {
        DefectComment defectComment = new DefectComment();
        defectComment.setCommentText("Text defektového komentáře");
        defectComment.setCreatedDateTime(LocalDateTime.now());
        defectCommentDao.saveDefectComment(defectComment);
        return defectComment;

    }

    private TCInstance createPilotTestCaseInstance() {
        TCInstance TCInstance = new TCInstance();
        TCInstance.setName(LocalDateTime.now().toString());
        testCaseInstanceDao.saveTestCaseInstance(TCInstance);
        return TCInstance;
    }

    private TCMuster createPilotTestCaseMuster() {
        TCMuster TCMuster = new TCMuster();
        TCMuster.setName(LocalDateTime.now().toString());
        testCaseMusterDao.saveTestCaseMuster(TCMuster);
        return TCMuster;
    }

    private Project createPilotTestProject() {
        Project project = new Project();
        project.setName(LocalDateTime.now().toString());
        testProjectDao.saveTestProject(project);
        return project;
    }

    private TSInstance createPilotTestStepInstance() {
        TSInstance TSInstance = new TSInstance();
        TSInstance.setAction(LocalDateTime.now().toString());
        TSInstance.setResult("Actual behavior of test step instance");
        testStepInstanceDao.saveTestStepInstance(TSInstance);
        return TSInstance;

    }

    private TSMuster createPilotTestStepMuster() {
        TSMuster TSMuster = new TSMuster();
        TSMuster.setAction(LocalDateTime.now().toString());
        TSMuster.setExpected("Expected behavior of test step muster");
        testStepMusterDao.saveTestStepMuster(TSMuster);
        return TSMuster;

    }

    private TestSuite createPilotTestSuit() {
        TestSuite testSuite = new TestSuite();
        testSuite.setName(LocalDateTime.now().toString());
        testSuiteDao.saveTestSuite(testSuite);
        return testSuite;

    }

}
