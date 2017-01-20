package cz.vse.service;

import cz.vse.dto.TCMusterDTO;
import cz.vse.dto.TestSuiteDTO;
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

    void createTestCaseMuster(TCMusterDTO tcMusterDTO);

    void updateTestCaseMuster(TCMuster tcMuster);

    void updateTestCaseMuster(TCMusterDTO tcMusterDTO);

    void deleteTestCaseMuster(TCMuster TCMusterToDelete);

    void deleteTestCaseMusterById(long testCaseMusterToDeleteById);

    TCMuster findTestCaseMusterById(long id);

    TCMusterDTO findTestCaseMusterDTOById(long id);

    List<TCMuster> findAllTestCaseMusters();

    List<TCMusterDTO> findAllTestCaseMustersDTO();

    TCMuster findTestCaseMusterByTCInstanceId(long tcInstanceId);

    List<TCMuster> findAllTestCaseMustersByTestSuite(TestSuite testSuite);

    List<TCMusterDTO> findAllTestCaseMustersDTOByTestSuiteId(long id);

    List<TCMusterDTO> findAllTCDTOByUser(Person person);

    List<TCMusterDTO> findAllTCDTOByUser(Long projectId);

    int getNumberOfMyTCsInProject(Person person, Project project);



}
