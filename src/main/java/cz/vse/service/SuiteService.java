package cz.vse.service;

import cz.vse.dao.TestSuiteDao;
import cz.vse.dao.impl.TestSuiteDaoImpl;
import cz.vse.dto.ProjectDTO;
import cz.vse.dto.TestSuiteDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.TestSuite;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
@Transactional
public class SuiteService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private TestSuiteDao testSuiteDao;

    @Autowired
    private PersonService personService;

    @Autowired
    private SuiteService projectService;

    public void createTestSuite(TestSuiteDTO testSuiteDTO) {
        l.debug("creating test suite - service");
        TestSuite testSuite = new TestSuite();
        testSuite = mapper.map(testSuiteDTO, TestSuite.class);
        testSuite.setCreatedDateTime(LocalDateTime.now());
        testSuiteDao.saveTestSuite(testSuite);

        /*List<Person> personMembersList = new ArrayList<>();
        if (project.getPersonMembers() != null) {
            for (Person personForId : project.getPersonMembers()) {
                Person person = personService.findPersonById(personForId.getId());
                person.addTestProjectMember(project);
                personMembersList.add(person);
            }
            project.setPersonMembers(personMembersList);
        }
        testProjectDao.saveTestProject(project);*/
        l.info("created test suite - service: " + testSuiteDTO);
    }

    public void createTestSuite(TestSuite testSuite) {
        l.debug("creating test suite - service");
        testSuiteDao.saveTestSuite(testSuite);
        l.info("created test suite - service: " + testSuite);
    }

    public void updateTestSuite(TestSuiteDTO testSuiteDTO) {
        l.debug("updating test suite - service");
        TestSuite testSuite = new TestSuite();
        testSuite = mapper.map(testSuiteDTO, TestSuite.class);
        testSuiteDao.updateTestSuite(testSuite);

       /* List<Person> personMembersList = new ArrayList<>();
        if (project.getPersonMembers() != null) {
            for (Person personForId : project.getPersonMembers()) {
                Person person = personService.findPersonById(personForId.getId());
                person.addTestProjectMember(project);
                personMembersList.add(person);
            }
            project.setPersonMembers(personMembersList);
        }
        testProjectDao.updateTestProject(project);*/
        l.info("updated test suite - service: " + testSuiteDTO);
    }

    public void deleteTestSuite(TestSuite testSuite) {
        l.debug("deleting test suite - service");
        Long testSuiteId = testSuite.getId();
        testSuiteDao.deleteTestSuite(testSuiteId);
        l.info("test suite deleted - service: " + testSuite);
    }

    public void deleteTestSuiteById(long id) {
        l.debug("deleting test suite - service");
        testSuiteDao.deleteTestSuite(id);
        l.info("test suite deleted - service: " + id);
    }

    public TestSuite findTestSuiteById(long id) {
        l.debug("finding test suite - service");
        TestSuite testSuite;
        testSuite = testSuiteDao.getTestSuiteById(id);
        l.info("test suit found - service: " + id + " - " + testSuite);
        return testSuite;
    }

    public TestSuiteDTO findTestSuiteDTOById(long id) {
        l.debug("finding test suite - service");
        TestSuite testSuite;
        TestSuiteDTO testSuiteDTO;
        testSuite = testSuiteDao.getTestSuiteById(id);
        testSuiteDTO = mapper.map(testSuite, TestSuiteDTO.class);

        l.info("projectDTO found - service: " + id + " - " + testSuiteDTO.toString());
        return testSuiteDTO;
    }

    public List<TestSuiteDTO> findAllTestSuitesDTO() {
        l.debug("finding all testSuites - service");
        List<TestSuite> testSuiteList;
        List<TestSuiteDTO> testSuiteDTOList;

        testSuiteList = testSuiteDao.getAllTestSuites();
        l.warn("mezikrok");
        testSuiteDTOList = mapper.mapAsList(testSuiteList, TestSuiteDTO.class);

        l.info("found all testSuites - service: " + testSuiteDTOList.toString());
        return testSuiteDTOList;
    }

    public List<TestSuite> findAllTestSuites() {
        l.debug("finding all testSuites - service");
        List<TestSuite> testSuites = new ArrayList<>();
        testSuites = testSuiteDao.getAllTestSuites();
        l.info("found all testSuites - service: " + testSuites.toString());
        return testSuites;
    }
}
