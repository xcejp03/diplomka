package cz.vse.service.impl;

import cz.vse.dto.TestSuiteDTO;
import cz.vse.dto.TestSuiteForm;
import cz.vse.dto.TestSuiteList;
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
        l.info("with: "+ testSuiteDTO);
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
        l.info("created: " + testSuite);
    }

    @Override
    public void createTestSuite(TestSuiteForm suiteForm) {
        l.info("with: "+ suiteForm);
        TestSuite testSuite = new TestSuite();
        mapper.map(suiteForm, testSuite);
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
        l.info("created: " + testSuite);
    }

    public void createTestSuite(TestSuite testSuite) {
        l.info("with: "+ testSuite);
        testSuiteRepository.save(testSuite);
        l.info("created: " + testSuite);
    }

    public void updateTestSuite(TestSuiteDTO testSuiteDTO) {
        l.info("with: "+ testSuiteDTO);
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
        l.info("updated: " + testSuite);
    }

    @Override
    public void updateTestSuite(TestSuiteForm suiteForm) {
        l.info("with: "+ suiteForm);
        TestSuite testSuite = testSuiteRepository.findOne(suiteForm.getId());
        mapper.map(suiteForm, testSuite);
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
        l.info("updated: " + testSuite);
    }

    public void deleteTestSuite(TestSuite testSuite) {
        l.info("with: "+ testSuite);
        testSuiteRepository.delete(testSuite);
        l.info("deleted: " + testSuite);
    }

    public void deleteTestSuiteById(long id) {
        l.info("with: "+ id);
        testSuiteRepository.delete(id);
        l.info("deleted: " + id);
    }

    public TestSuite findTestSuiteById(long id) {
        l.info("with: "+ id);
        TestSuite testSuite;
        testSuite = testSuiteRepository.findOne(id);
        l.info("found: "+ testSuite);
        return testSuite;
    }

    public TestSuiteDTO findTestSuiteDTOById(long id) {
        l.info("with: "+ id);
        TestSuite testSuite;
        TestSuiteDTO testSuiteDTO;
        testSuite = testSuiteRepository.findOne(id);
        testSuiteDTO = mapper.map(testSuite, TestSuiteDTO.class);
        l.info("found: "+ testSuiteDTO);
        return testSuiteDTO;
    }

    @Override
    public TestSuiteForm findTestSuiteFormById(long id) {
        l.info("with: "+ id);
        TestSuite testSuite;
        TestSuiteForm suiteForm;
        testSuite = testSuiteRepository.findOne(id);
        suiteForm = mapper.map(testSuite, TestSuiteForm.class);
        l.info("found: "+ suiteForm);
        return suiteForm;
    }

    @Override
    public TestSuiteList findTestSuiteListById(long id) {
        l.info("with: "+ id);
        TestSuite testSuite;
        TestSuiteList testSuiteList;
        testSuite = testSuiteRepository.findOne(id);
        testSuiteList = mapper.map(testSuite, TestSuiteList.class);
        l.info("found: "+ testSuiteList);
        return testSuiteList;
    }

    public List<TestSuiteDTO> findAllTestSuitesDTO() {
        List<TestSuite> testSuiteList;
        List<TestSuiteDTO> testSuiteDTOList;

        testSuiteList = testSuiteRepository.findAll();
        l.warn("mezikrok");
        testSuiteDTOList = mapper.mapAsList(testSuiteList, TestSuiteDTO.class);
        l.info("found: "+ testSuiteDTOList);
        return testSuiteDTOList;
    }

    public List<TestSuite> findAllTestSuites() {
        List<TestSuite> testSuites;
        testSuites = testSuiteRepository.findAll();
        l.info("found: "+ testSuites);
        return testSuites;
    }

    public List<TestSuite> findAllTestSuitesByProjectId(Long projectId) {
        l.info("with: "+ projectId);
        List<TestSuite> testSuiteList;
        Project project = projectService.findTestProjectById(projectId);
        testSuiteList = testSuiteRepository.findAllTestSuitesByProjectOrderById(project);
        l.info("found: "+ testSuiteList);
        return testSuiteList;
    }

    @Override
    public List<TestSuiteList> findAllTestSuiteListsByProjectId(Long projectId) {
        l.info("with: "+projectId);
        List<TestSuiteList> suiteLists;
        Project project = projectService.findTestProjectById(projectId);
        List<TestSuite> testSuites = testSuiteRepository.findAllTestSuitesByProjectOrderById(project);
        suiteLists = mapper.mapAsList(testSuites, TestSuiteList.class);
        l.info("found: "+suiteLists);
        return suiteLists;
    }

    public List<TestSuiteDTO> findAllTestSuitesDTOByProjectId(Long projectId) {
        l.info("with: "+ projectId);
        List<TestSuiteDTO> testSuiteDTOList;
        List<TestSuite> testSuiteList;
        Project project = projectService.findTestProjectById(projectId);
        testSuiteList = testSuiteRepository.findAllTestSuitesByProjectOrderById(project);
        testSuiteDTOList = mapper.mapAsList(testSuiteList, TestSuiteDTO.class);
        l.info("found: "+ testSuiteDTOList);
        return testSuiteDTOList;
    }

    @Override
    public List<TestSuiteDTO> findAllTestSuitesDTOByUser(Person person) {
        l.info("with: "+ person);
        List<Project> projectList = person.getProjectsMember();
        List<TestSuite> testSuiteList = testSuiteRepository.findAllTestSuitesByProjectIn(projectList);
        List<TestSuiteDTO> testSuiteDTOList;
        testSuiteDTOList = mapper.mapAsList(testSuiteList, TestSuiteDTO.class);
        l.info("found: "+ testSuiteDTOList);
        return  testSuiteDTOList;
    }

    @Override
    public List<TestSuiteList> findAllTestSuiteListsByUser(Person person) {
        l.info("with: "+ person);
        List<Project> projectList = person.getProjectsMember();
        List<TestSuite> testSuiteList = testSuiteRepository.findAllTestSuitesByProjectIn(projectList);
        List<TestSuiteList> suiteLists;
        suiteLists = mapper.mapAsList(testSuiteList, TestSuiteList.class);
        l.info("found: "+ suiteLists);
        return  suiteLists;
    }

    @Override
    public List<TestSuiteDTO> findAllTestSuitesDTOByUser(Long personId) {
        l.info("with: "+ personId);
        Person person = personService.findPersonById(personId);
        List<TestSuiteDTO> testSuiteDTOList = findAllTestSuitesDTOByUser(person);
        l.info("found: "+ testSuiteDTOList);
        return testSuiteDTOList;
    }

    @Override
    public int getNumberOfSuitesInProject(Project project) {
        l.info("with: "+ project);
        return testSuiteRepository.getNumberOfSuitesInProject(project);
    }

    @Override
    public int getNumberOfSuitesInProject(long id) {
        l.info("with: "+ id);
        Project project = projectService.findTestProjectById(id);
        return testSuiteRepository.getNumberOfSuitesInProject(project);
    }


}

