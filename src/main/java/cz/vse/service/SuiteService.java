package cz.vse.service;

import cz.vse.dto.TestSuiteDTO;
import cz.vse.entity.Person;
import cz.vse.entity.TestSuite;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface SuiteService {

    void createTestSuite(TestSuiteDTO testSuiteDTO);

    void createTestSuite(TestSuite testSuite);

    void updateTestSuite(TestSuiteDTO testSuiteDTO);

    void deleteTestSuite(TestSuite testSuite);

    void deleteTestSuiteById(long id);

    TestSuite findTestSuiteById(long id);

    TestSuiteDTO findTestSuiteDTOById(long id);

    List<TestSuiteDTO> findAllTestSuitesDTO();

    List<TestSuite> findAllTestSuites();

    List<TestSuite> findAllTestSuitesByProjectId(Long projectId);

    List<TestSuiteDTO> findAllTestSuitesDTOByProjectId(Long projectId);

    List<TestSuiteDTO> findAllTestSuitesDTOByUser(Person person);

    List<TestSuiteDTO> findAllTestSuitesDTOByUser(Long projectId);

}
