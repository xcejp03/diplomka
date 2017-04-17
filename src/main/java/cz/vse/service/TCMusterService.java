package cz.vse.service;

import cz.vse.dto.TCMusterDTO;
import cz.vse.dto.TCMusterForm;
import cz.vse.dto.TCMusterList;
import cz.vse.dto.TCMusterName;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.TCMuster;
import cz.vse.entity.TestSuite;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TCMusterService {

    void createTestCaseMuster(TCMuster tcMuster);

    void createTestCaseMuster(TCMusterForm tcMusterForm);

    void createTestCaseMuster(List<TCMuster> tcMusters);

    void createTestCaseMuster(TCMusterDTO tcMusterDTO);

    void updateTestCaseMuster(TCMuster tcMuster);

    void updateTestCaseMuster(TCMusterDTO tcMusterDTO);

    void updateTestCaseMuster(TCMusterForm tcMusterForm);

    void deleteTestCaseMuster(TCMuster TCMusterToDelete);

    void deleteTestCaseMusterById(long testCaseMusterToDeleteById);

    TCMuster findTestCaseMusterById(long id);

    TCMusterName findTestCaseMusterNameById(long id);

    TCMusterDTO findTestCaseMusterDTOById(long id);

    TCMusterList findTCMusterListById(long id);

    TCMusterForm findTestCaseMusterFormById(long id);

    List<TCMuster> findAllTestCaseMusters();

    List<TCMusterDTO> findAllTestCaseMustersDTO();

    List<TCMusterName> findAllTestCaseMustersNames();

    TCMuster findTestCaseMusterByTCInstanceId(long tcInstanceId);

    List<TCMuster> findAllTestCaseMustersByTestSuite(TestSuite testSuite);

    List<TCMusterDTO> findAllTestCaseMustersDTOByTestSuiteId(long id);

    List<TCMusterName> findAllTestCaseMusterNamesByTestSuiteId(long id);

    List<TCMusterDTO> findAllTCDTOByUser(Person person);

    List<TCMusterList> findAllTCListsByUser(Person person);

    List<TCMusterDTO> findAllTCDTOByUser(Long projectId);

    List<TCMusterDTO> findTCMustersDTOByProject(Project project);

    List<TCMusterDTO> findTCMustersDTOByProject(List<Project> projects);

    List<TCMusterDTO> findTCMustersDTOByProjectId(long projectId);

    List<TCMusterList> findTCMusterListsByProject(List<Project> projects);

    List<TCMusterList> findTCMusterListByProject(Project project);

    List<TCMusterName> findTCMusterNamesByProjectId(long projectId);

    int getNumberOfMyTCsInProject(Person person, Project project);

    int getNumberOfTCsInProject(Project project);


}
