package cz.vse.repository;

        import cz.vse.entity.TestSuite;
        import cz.vse.repository.base.BaseRepository;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface TestSuiteRepository extends BaseRepository<TestSuite> {
    TestSuite findById(Long id);
}
