package cz.vse.service.impl;

import cz.vse.dto.TCMusterDTO;
import cz.vse.dto.TCMusterForm;
import cz.vse.dto.TCMusterList;
import cz.vse.dto.TCMusterName;
import cz.vse.entity.*;
import cz.vse.repository.TCMusterRepository;
import cz.vse.service.*;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class TCMusterServiceImpl implements TCMusterService {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private TCMusterRepository tcMusterRepository;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private SuiteService suiteService;

    @Autowired
    private TCInstanceService tcInstanceService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ProjectService projectService;

    public void createTestCaseMuster(TCMuster tcMuster) {
        l.info("with: " + tcMuster);
        tcMuster.setCreatedDateTime(LocalDateTime.now());
        tcMusterRepository.save(tcMuster);
        l.info("created: " + tcMuster);
    }

    @Override
    public void createTestCaseMuster(TCMusterForm tcMusterForm) {
        l.info("with: " + tcMusterForm);
        TCMuster tcMuster = mapper.map(tcMusterForm, TCMuster.class);
        tcMuster.setCreatedDateTime(LocalDateTime.now());
        TestSuite testSuite = suiteService.findTestSuiteById(tcMusterForm.getSuiteFrom());
        tcMuster.setTestSuites(Arrays.asList(testSuite));

        TCMuster tcMusterSaved = tcMusterRepository.save(tcMuster);
//        updateTestSuites(tcMusterForm.getSuiteFrom(), tcMusterSaved);
        l.info("created: " + tcMuster);
    }

    public void createTestCaseMuster(List<TCMuster> tcMusters) {
        l.info("with: " + tcMusters);
        for (TCMuster tcMuster : tcMusters) {
            tcMuster.setCreatedDateTime(LocalDateTime.now());
        }
        tcMusterRepository.save(tcMusters);
        l.info("created: " + tcMusters);
    }


    public void createTestCaseMuster(TCMusterDTO tcMusterDTO) {
        l.info("with: " + tcMusterDTO);
        TCMuster tcMuster;
        tcMuster = mapper.map(tcMusterDTO, TCMuster.class);
        tcMuster.setCreatedDateTime(LocalDateTime.now());
        tcMusterRepository.save(tcMuster);
        l.info("with: " + tcMuster);
    }

    public void updateTestCaseMuster(TCMuster tcMuster) {
        l.info("with: " + tcMuster);
        tcMuster.setUpdatedDateTime(LocalDateTime.now());
        tcMusterRepository.save(tcMuster);
        l.info("updated: " + tcMuster);
    }

    public void updateTestCaseMuster(TCMusterDTO tcMusterDTO) {
        l.info("with: " + tcMusterDTO);
        TCMuster tcMuster = tcMusterRepository.findOne(tcMusterDTO.getId());
        mapper.map(tcMusterDTO, tcMuster);
        tcMuster.setUpdatedDateTime(LocalDateTime.now());
        tcMusterRepository.save(tcMuster);
        l.info("updated: " + tcMuster);
    }

    @Override
    public void updateTestCaseMuster(TCMusterForm tcMusterForm) {
        l.info("with: " + tcMusterForm);
        TCMuster tcMuster = tcMusterRepository.findOne(tcMusterForm.getId());
        mapper.map(tcMusterForm, tcMuster);
        tcMuster.setUpdatedDateTime(LocalDateTime.now());
        tcMusterRepository.save(tcMuster);
    }

    public void deleteTestCaseMuster(TCMuster tcMusterToDelete) {
        l.info("with: " + tcMusterToDelete);
        tcMusterRepository.delete(tcMusterToDelete);
        l.info("deleted: " + tcMusterToDelete);
    }

    public void deleteTestCaseMusterById(long testCaseMusterToDeleteById) {
        l.info("with: " + testCaseMusterToDeleteById);
        TCMuster TCMusterToDelete;
        tcMusterRepository.delete(testCaseMusterToDeleteById);
        l.info("deleted: " + testCaseMusterToDeleteById);
    }

    public TCMuster findTestCaseMusterById(long id) {
        l.info("with: " + id);
        TCMuster tcMuster;
        tcMuster = tcMusterRepository.findOne(id);
        l.info("found: " + tcMuster);
        return tcMuster;
    }

    @Override
    public TCMusterName findTestCaseMusterNameById(long id) {
        l.info("with: " + id);
        TCMusterName tcMusterName;
        TCMuster tcMuster = tcMusterRepository.findOne(id);
        tcMusterName = mapper.map(tcMuster, TCMusterName.class);
        l.info("found: " + tcMusterName);
        return tcMusterName;
    }

    public TCMusterDTO findTestCaseMusterDTOById(long id) {
        l.info("with: " + id);
        TCMuster tcMuster;
        TCMusterDTO tcMusterDTO;
        tcMuster = tcMusterRepository.findOne(id);
        tcMusterDTO = mapper.map(tcMuster, TCMusterDTO.class);
        l.info("found: " + tcMusterDTO);
        return tcMusterDTO;
    }

    @Override
    public TCMusterList findTCMusterListById(long id) {
        l.info("with: " + id);
        TCMusterList tcMusterList;
        TCMuster tcMuster = tcMusterRepository.findOne(id);
        tcMusterList = mapper.map(tcMuster, TCMusterList.class);
        l.info("found: " + tcMusterList);
        return tcMusterList;
    }

    @Override
    public TCMusterForm findTestCaseMusterFormById(long id) {
        l.info("with: " + id);
        TCMuster tcMuster;
        TCMusterForm tcMusterForm;
        tcMuster = tcMusterRepository.findOne(id);
        tcMusterForm = mapper.map(tcMuster, TCMusterForm.class);
        l.info("found: " + tcMusterForm);
        return tcMusterForm;
    }

    public List<TCMuster> findAllTestCaseMusters() {
        List<TCMuster> tcMusterList;
        tcMusterList = tcMusterRepository.findAll();
        l.info("found : " + tcMusterList);
        return tcMusterList;
    }

    public List<TCMusterDTO> findAllTestCaseMustersDTO() {
        List<TCMuster> tcMusterList;
        List<TCMusterDTO> tcMusterDTOList;
        tcMusterList = tcMusterRepository.findAll();
        tcMusterDTOList = mapper.mapAsList(tcMusterList, TCMusterDTO.class);
        l.info("found: " + tcMusterDTOList);
        return tcMusterDTOList;
    }

    @Override
    public List<TCMusterName> findAllTestCaseMustersNames() {
        List<TCMuster> tcMusterList;
        List<TCMusterName> tcMusterNames;
        tcMusterList = tcMusterRepository.findAll();
        tcMusterNames = mapper.mapAsList(tcMusterList, TCMusterName.class);
        l.info("found: " + tcMusterNames);
        return tcMusterNames;
    }

    public TCMuster findTestCaseMusterByTCInstanceId(long tcInstanceId) {
        l.info("with: " + tcInstanceId);
        TCInstance tcInstance;
        TCMuster tcMuster;
        tcInstance = tcInstanceService.findTestCaseInstanceById(tcInstanceId);
        tcMuster = findTestCaseMusterById(tcInstance.gettCMuster().getId());
        l.info("found: " + tcMuster);
        return tcMuster;
    }

    @Override
    public List<TCMuster> findAllTestCaseMustersByTestSuite(TestSuite testSuite) {
        l.info("with: " + testSuite);
        List<TCMuster> tcMusterList;
        tcMusterList = tcMusterRepository.findAllTCMustersByTestSuitesOrderById(testSuite);
        l.info("found: " + tcMusterList);
        return tcMusterList;
    }

    @Override
    public List<TCMusterDTO> findAllTestCaseMustersDTOByTestSuiteId(long id) {
        l.info("with: " + id);
        TestSuite testSuite;
        List<TCMuster> tcMusterList;
        List<TCMusterDTO> tcMusterDTOList;
        testSuite = suiteService.findTestSuiteById(id);
        tcMusterList = findAllTestCaseMustersByTestSuite(testSuite);
        tcMusterDTOList = mapper.mapAsList(tcMusterList, TCMusterDTO.class);
        l.info("found: " + tcMusterDTOList);
        return tcMusterDTOList;
    }

    @Override
    public List<TCMusterName> findAllTestCaseMusterNamesByTestSuiteId(long id) {
        l.info("with: " + id);
        TestSuite testSuite;
        List<TCMuster> tcMusterList;
        List<TCMusterName> tcMusterNames;
        testSuite = suiteService.findTestSuiteById(id);
        tcMusterList = findAllTestCaseMustersByTestSuite(testSuite);
        tcMusterNames = mapper.mapAsList(tcMusterList, TCMusterName.class);
        l.info("found: " + tcMusterNames);
        return tcMusterNames;
    }

    @Override
    public List<TCMusterDTO> findAllTCDTOByUser(Person person) {
        l.info("with: " + person);
        List<Project> projectList = person.getProjectsMember();
        List<TCMuster> tcMusterList = tcMusterRepository.findAllTCByProjectIn(projectList);
        List<TCMusterDTO> tcMusterDTOList;
        tcMusterDTOList = mapper.mapAsList(tcMusterList, TCMusterDTO.class);
        l.info("found: " + tcMusterDTOList);
        return tcMusterDTOList;
    }

    @Override
    public List<TCMusterList> findAllTCListsByUser(Person person) {
        l.info("with: " + person);
        List<Project> projectList = person.getProjectsMember();
        List<TCMuster> tcMusterList = tcMusterRepository.findAllTCByProjectIn(projectList);
        List<TCMusterList> tcMusterLists;
        tcMusterLists = mapper.mapAsList(tcMusterList, TCMusterList.class);
        l.info("found: " + tcMusterLists);
        return tcMusterLists;
    }

    @Override
    public List<TCMusterDTO> findAllTCDTOByUser(Long personId) {
        l.info("with: " + personId);
        Person person = personService.findPersonById(personId);
        List<TCMusterDTO> tcMusterDTOList;
        tcMusterDTOList = findAllTCDTOByUser(person);
        l.info("found: " + tcMusterDTOList);
        return tcMusterDTOList;
    }

    @Override
    public int getNumberOfMyTCsInProject(Person person, Project project) {
        l.info("with: " + person + " and " + project);
        return tcMusterRepository.getNumberOfMyTCsInProject(person, project);
    }

    @Override
    public int getNumberOfTCsInProject(Project project) {
        l.info("with: " + project);
        return tcMusterRepository.getNumberOfTCsInProject(project);
    }

    @Override
    public List<TCMusterDTO> findTCMustersDTOByProject(Project project) {
        l.info("with: " + project);
        List<TCMuster> tcMusters = tcMusterRepository.findAllTCByProjectIn(Arrays.asList(project));
        List<TCMusterDTO> tcMustersDTO = mapper.mapAsList(tcMusters, TCMusterDTO.class);
        l.info("found: " + tcMustersDTO);
        return tcMustersDTO;
    }

    @Override
    public List<TCMusterDTO> findTCMustersDTOByProject(List<Project> projects) {
        l.info("with: " + projects);
        List<TCMuster> tcMusters = tcMusterRepository.findAllTCByProjectIn(projects);
        List<TCMusterDTO> tcMustersDTO = mapper.mapAsList(tcMusters, TCMusterDTO.class);
        l.info("found: " + tcMustersDTO);
        return tcMustersDTO;
    }

    @Override
    public List<TCMusterDTO> findTCMustersDTOByProjectId(long projectId) {
        l.info("with: " + projectId);
        Project project = projectService.findTestProjectById(projectId);
        List<TCMuster> tcMusters = tcMusterRepository.findAllTCByProjectIn(Arrays.asList(project));
        List<TCMusterDTO> tcMustersDTO = mapper.mapAsList(tcMusters, TCMusterDTO.class);
        l.info("found: " + tcMustersDTO);
        return tcMustersDTO;
    }

    @Override
    public List<TCMusterList> findTCMusterListsByProject(List<Project> projects) {
        l.info("with: " + projects);
        List<TCMuster> tcMusters = tcMusterRepository.findAllTCByProjectIn(projects);
        List<TCMusterList> tcMusterLists = mapper.mapAsList(tcMusters, TCMusterList.class);
        l.info("found: " + tcMusterLists);
        return tcMusterLists;
    }

    @Override
    public List<TCMusterList> findTCMusterListByProject(Project project) {
        l.info("with: " + project);
        List<TCMuster> tcMusters = tcMusterRepository.findAllTCByProjectIn(Arrays.asList(project));
        List<TCMusterList> tcMusterLists = mapper.mapAsList(tcMusters, TCMusterList.class);
        l.info("found: " + tcMusterLists);
        return tcMusterLists;
    }

    @Override
    public List<TCMusterName> findTCMusterNamesByProjectId(long projectId) {
        l.info("with: " + projectId);
        Project project = projectService.findTestProjectById(projectId);
        List<TCMuster> tcMusters = tcMusterRepository.findAllTCByProjectIn(Arrays.asList(project));
        List<TCMusterName> tcMusterNames = mapper.mapAsList(tcMusters, TCMusterName.class);
        l.info("found: " + tcMusterNames);
        return tcMusterNames;
    }

    private void updateTestSuites(Long id, TCMuster tcMuster) {
        l.info("with: " + id + " - " + tcMuster);
        suiteService.addTCToTestSuite(id, tcMuster);
        l.info("updated");
    }
}
