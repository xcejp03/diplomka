package cz.vse.repository;

import cz.vse.entity.Project;
import cz.vse.entity.TestSuite;
import cz.vse.repository.base.BaseRepository;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface SuiteRepository extends BaseRepository<TestSuite> {
    List<TestSuite> findAllTestSuitesByProjectOrderById(Project project);



}
