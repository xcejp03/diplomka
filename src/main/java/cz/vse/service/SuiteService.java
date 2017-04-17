package cz.vse.service;

import cz.vse.dto.SuiteDTO;
import cz.vse.dto.SuiteForm;
import cz.vse.dto.SuiteList;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.TCMuster;
import cz.vse.entity.TestSuite;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface SuiteService {

    void createTestSuite(SuiteDTO suiteDTO);

    void createTestSuite(SuiteForm suiteForm);

    void createTestSuite(TestSuite testSuite);

    void updateTestSuite(SuiteDTO suiteDTO);

    void updateTestSuite(SuiteForm suiteForm);

    void deleteTestSuite(TestSuite testSuite);

    void deleteTestSuiteById(long id);

    TestSuite findTestSuiteById(long id);

    SuiteDTO findTestSuiteDTOById(long id);

    SuiteForm findTestSuiteFormById(long id);

    SuiteList findTestSuiteListById(long id);

    List<SuiteDTO> findAllTestSuitesDTO();

    List<TestSuite> findAllTestSuites();

    List<TestSuite> findAllTestSuitesByProjectId(Long projectId);

    List<SuiteList> findAllTestSuiteListsByProjectId(Long projectId);

    List<SuiteDTO> findAllTestSuitesDTOByProjectId(Long projectId);

    List<SuiteDTO> findAllTestSuitesDTOByUser(Person person);

    List<SuiteList> findAllTestSuiteListsByUser(Person person);

    List<SuiteDTO> findAllTestSuitesDTOByUser(Long projectId);

    int getNumberOfSuitesInProject(Project project);

    int getNumberOfSuitesInProject(long id);

    void addTCToTestSuite(Long id, TCMuster tcMuster);

}
