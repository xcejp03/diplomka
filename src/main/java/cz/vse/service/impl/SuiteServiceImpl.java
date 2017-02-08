package cz.vse.service.impl;

import cz.vse.dto.SuiteDTO;
import cz.vse.dto.SuiteForm;
import cz.vse.dto.SuiteList;
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

    public void createTestSuite(SuiteDTO suiteDTO) {
        l.info("with: "+ suiteDTO);
        TestSuite testSuite = new TestSuite();
        mapper.map(suiteDTO, testSuite);
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
    public void createTestSuite(SuiteForm suiteForm) {
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

    public void updateTestSuite(SuiteDTO suiteDTO) {
        l.info("with: "+ suiteDTO);
        TestSuite testSuite = testSuiteRepository.findOne(suiteDTO.getId());
        mapper.map(suiteDTO, testSuite);
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
    public void updateTestSuite(SuiteForm suiteForm) {
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

    public SuiteDTO findTestSuiteDTOById(long id) {
        l.info("with: "+ id);
        TestSuite testSuite;
        SuiteDTO suiteDTO;
        testSuite = testSuiteRepository.findOne(id);
        suiteDTO = mapper.map(testSuite, SuiteDTO.class);
        l.info("found: "+ suiteDTO);
        return suiteDTO;
    }

    @Override
    public SuiteForm findTestSuiteFormById(long id) {
        l.info("with: "+ id);
        TestSuite testSuite;
        SuiteForm suiteForm;
        testSuite = testSuiteRepository.findOne(id);
        suiteForm = mapper.map(testSuite, SuiteForm.class);
        l.info("found: "+ suiteForm);
        return suiteForm;
    }

    @Override
    public SuiteList findTestSuiteListById(long id) {
        l.info("with: "+ id);
        TestSuite testSuite;
        SuiteList suiteList;
        testSuite = testSuiteRepository.findOne(id);
        suiteList = mapper.map(testSuite, SuiteList.class);
        l.info("found: "+ suiteList);
        return suiteList;
    }

    public List<SuiteDTO> findAllTestSuitesDTO() {
        List<TestSuite> testSuiteList;
        List<SuiteDTO> suiteDTOList;

        testSuiteList = testSuiteRepository.findAll();
        l.warn("mezikrok");
        suiteDTOList = mapper.mapAsList(testSuiteList, SuiteDTO.class);
        l.info("found: "+ suiteDTOList);
        return suiteDTOList;
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
    public List<SuiteList> findAllTestSuiteListsByProjectId(Long projectId) {
        l.info("with: "+projectId);
        List<SuiteList> suiteLists;
        Project project = projectService.findTestProjectById(projectId);
        List<TestSuite> testSuites = testSuiteRepository.findAllTestSuitesByProjectOrderById(project);
        suiteLists = mapper.mapAsList(testSuites, SuiteList.class);
        l.info("found: "+suiteLists);
        return suiteLists;
    }

    public List<SuiteDTO> findAllTestSuitesDTOByProjectId(Long projectId) {
        l.info("with: "+ projectId);
        List<SuiteDTO> suiteDTOList;
        List<TestSuite> testSuiteList;
        Project project = projectService.findTestProjectById(projectId);
        testSuiteList = testSuiteRepository.findAllTestSuitesByProjectOrderById(project);
        suiteDTOList = mapper.mapAsList(testSuiteList, SuiteDTO.class);
        l.info("found: "+ suiteDTOList);
        return suiteDTOList;
    }

    @Override
    public List<SuiteDTO> findAllTestSuitesDTOByUser(Person person) {
        l.info("with: "+ person);
        List<Project> projectList = person.getProjectsMember();
        List<TestSuite> testSuiteList = testSuiteRepository.findAllTestSuitesByProjectIn(projectList);
        List<SuiteDTO> suiteDTOList;
        suiteDTOList = mapper.mapAsList(testSuiteList, SuiteDTO.class);
        l.info("found: "+ suiteDTOList);
        return suiteDTOList;
    }

    @Override
    public List<SuiteList> findAllTestSuiteListsByUser(Person person) {
        l.info("with: "+ person);
        List<Project> projectList = person.getProjectsMember();
        List<TestSuite> testSuiteList = testSuiteRepository.findAllTestSuitesByProjectIn(projectList);
        List<SuiteList> suiteLists;
        suiteLists = mapper.mapAsList(testSuiteList, SuiteList.class);
        l.info("found: "+ suiteLists);
        return  suiteLists;
    }

    @Override
    public List<SuiteDTO> findAllTestSuitesDTOByUser(Long personId) {
        l.info("with: "+ personId);
        Person person = personService.findPersonById(personId);
        List<SuiteDTO> suiteDTOList = findAllTestSuitesDTOByUser(person);
        l.info("found: "+ suiteDTOList);
        return suiteDTOList;
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

