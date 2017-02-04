package cz.vse.service;

import cz.vse.dto.TestSuiteForm;
import cz.vse.dto.TestSuiteList;
import cz.vse.dto.old.TestSuiteDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.TestSuite;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface SuiteService {

    void createTestSuite(TestSuiteDTO testSuiteDTO);

    void createTestSuite(TestSuiteForm suiteForm);

    void createTestSuite(TestSuite testSuite);

    void updateTestSuite(TestSuiteDTO testSuiteDTO);

    void updateTestSuite(TestSuiteForm suiteForm);

    void deleteTestSuite(TestSuite testSuite);

    void deleteTestSuiteById(long id);

    TestSuite findTestSuiteById(long id);

    TestSuiteDTO findTestSuiteDTOById(long id);

    TestSuiteForm findTestSuiteFormById(long id);

    TestSuiteList findTestSuiteListById(long id);

    List<TestSuiteDTO> findAllTestSuitesDTO();

    List<TestSuite> findAllTestSuites();

    List<TestSuite> findAllTestSuitesByProjectId(Long projectId);

    List<TestSuiteList> findAllTestSuiteListsByProjectId(Long projectId);

    List<TestSuiteDTO> findAllTestSuitesDTOByProjectId(Long projectId);

    List<TestSuiteDTO> findAllTestSuitesDTOByUser(Person person);
    List<TestSuiteList> findAllTestSuiteListsByUser(Person person);

    List<TestSuiteDTO> findAllTestSuitesDTOByUser(Long projectId);

    int getNumberOfSuitesInProject(Project project);

    int getNumberOfSuitesInProject(long id);

}
