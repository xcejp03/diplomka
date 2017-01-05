package cz.vse.repository;

import cz.vse.entity.Defect;
import cz.vse.entity.Person;
import cz.vse.repository.base.BaseRepository;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface DefectRepository extends BaseRepository<Defect> {
    List<Defect> findAllDefectDTOByReporter(Person person);
    List<Defect>findAllDefectDTOByAssignee(Person person);
}
