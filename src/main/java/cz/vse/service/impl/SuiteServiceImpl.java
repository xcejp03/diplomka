package cz.vse.service.impl;

import cz.vse.dto.TestSuiteDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.TCMuster;
import cz.vse.entity.TestSuite;
import cz.vse.repository.TestSuiteRepository;
import cz.vse.repository.TCMusterRepository;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
import cz.vse.service.TCMusterService;
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
public class SuiteServiceImpl implements SuiteService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    TestSuiteRepository testSuiteRepository;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private PersonService personService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TCMusterService tcMusterService;

    @Autowired
    TCMusterRepository tcMusterRepository;

    public void createTestSuite(TestSuiteDTO testSuiteDTO) {
        l.debug("creating test suite - service");
        TestSuite testSuite = new TestSuite();
        mapper.map(testSuiteDTO, testSuite);
        testSuite.setCreatedDateTime(LocalDateTime.now());
        testSuiteRepository.save(testSuite);
        List<TCMuster> tcMusterList = new ArrayList<>();
        if (testSuite.getTcMusters() != null) {
            for (TCMuster tcMusterForId : testSuite.getTcMusters()) {
                TCMuster tcMuster = tcMusterRepository.findOne(tcMusterForId.getId());
                tcMuster.addTestSuites(testSuite);
                tcMusterList.add(tcMuster);
            }
            testSuite.setTcMusters(tcMusterList);
        }
        testSuiteRepository.save(testSuite);
        l.info("created test suite - service: " + testSuiteDTO);
    }

    public void createTestSuite(TestSuite testSuite) {
        l.debug("creating test suite - service");
        testSuiteRepository.save(testSuite);
        l.info("created test suite - service: " + testSuite);
    }

    public void updateTestSuite(TestSuiteDTO testSuiteDTO) {
        l.debug("updating test suite - service");
        TestSuite testSuite = testSuiteRepository.findOne(testSuiteDTO.getId());
        mapper.map(testSuiteDTO, testSuite);
        List<TCMuster> tcMusterList = new ArrayList<>();
        if (testSuite.getTcMusters() != null) {
            for (TCMuster tcMusterForId : testSuite.getTcMusters()) {
                TCMuster tcMuster = tcMusterService.findTestCaseMusterById(tcMusterForId.getId());
                tcMuster.addTestSuites(testSuite);

                tcMusterList.add(tcMuster);
            }
            testSuite.setTcMusters(tcMusterList);
        }
        testSuite.setUpdatedDateTime(LocalDateTime.now());
        testSuiteRepository.save(testSuite);
        l.info("updated test suite - service: " + testSuiteDTO);
    }

    public void deleteTestSuite(TestSuite testSuite) {
        l.debug("deleting test suite - service");
        Long testSuiteId = testSuite.getId();
        testSuiteRepository.delete(testSuiteId);
        l.info("test suite deleted - service: " + testSuite);
    }

    public void deleteTestSuiteById(long id) {
        l.debug("deleting test suite - service");
        testSuiteRepository.delete(id);
        l.info("test suite deleted - service: " + id);
    }

    public TestSuite findTestSuiteById(long id) {
        l.debug("finding test suite - service");
        TestSuite testSuite;
        testSuite = testSuiteRepository.findOne(id);
        l.info("test suit found - service: " + id + " - " + testSuite);
        return testSuite;
    }

    public TestSuiteDTO findTestSuiteDTOById(long id) {
        l.debug("finding test suite - service");
        TestSuite testSuite;
        TestSuiteDTO testSuiteDTO;
        testSuite = testSuiteRepository.findOne(id);
        testSuiteDTO = mapper.map(testSuite, TestSuiteDTO.class);

        l.info("projectDTO found - service: " + id + " - " + testSuiteDTO.toString());
        return testSuiteDTO;
    }

    public List<TestSuiteDTO> findAllTestSuitesDTO() {
        l.debug("finding all testSuites - service");
        List<TestSuite> testSuiteList;
        List<TestSuiteDTO> testSuiteDTOList;

        testSuiteList = testSuiteRepository.findAll();
        l.warn("mezikrok");
        testSuiteDTOList = mapper.mapAsList(testSuiteList, TestSuiteDTO.class);

        l.info("found all testSuites - service: " + testSuiteDTOList.toString());
        return testSuiteDTOList;
    }

    public List<TestSuite> findAllTestSuites() {
        l.debug("finding all testSuites - service");
        List<TestSuite> testSuites;
        testSuites = testSuiteRepository.findAll();
        l.info("found all testSuites - service: " + testSuites.toString());
        return testSuites;
    }

    public List<TestSuite> findAllTestSuitesByProjectId(Long projectId) {
        List<TestSuite> testSuiteList;
        Project project = projectService.findTestProjectById(projectId);
        testSuiteList = testSuiteRepository.findAllTestSuitesByProjectOrderById(project);
        return testSuiteList;
    }

    public List<TestSuiteDTO> findAllTestSuitesDTOByProjectId(Long projectId) {
        List<TestSuiteDTO> testSuiteDTOList;
        List<TestSuite> testSuiteList;
        Project project = projectService.findTestProjectById(projectId);
        testSuiteList = testSuiteRepository.findAllTestSuitesByProjectOrderById(project);
        testSuiteDTOList = mapper.mapAsList(testSuiteList, TestSuiteDTO.class);
        return testSuiteDTOList;
    }

    @Override
    public List<TestSuiteDTO> findAllTestSuitesDTOByUser(Person person) {
        List<Project> projectList = person.getProjectsMember();
        List<TestSuite> testSuiteList = testSuiteRepository.findAllTestSuitesByProjectIn(projectList);
        List<TestSuiteDTO> testSuiteDTOList;
        testSuiteDTOList = mapper.mapAsList(testSuiteList, TestSuiteDTO.class);
        return  testSuiteDTOList;
    }

    @Override
    public List<TestSuiteDTO> findAllTestSuitesDTOByUser(Long personId) {
        Person person = personService.findPersonById(personId);
        List<TestSuiteDTO> testSuiteDTOList = findAllTestSuitesDTOByUser(person);
        return testSuiteDTOList;
    }

    @Override
    public int getNumberOfSuitesInProject(Project project) {
        return testSuiteRepository.getNumberOfSuitesInProject(project);
    }

    @Override
    public int getNumberOfSuitesInProject(long id) {
        Project project = projectService.findTestProjectById(id);
        return testSuiteRepository.getNumberOfSuitesInProject(project);
    }


}

