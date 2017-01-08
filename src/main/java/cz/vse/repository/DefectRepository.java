package cz.vse.repository;

import cz.vse.entity.Defect;
import cz.vse.entity.DefectStatusEnum;
import cz.vse.entity.Person;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface DefectRepository extends BaseRepository<Defect> {
    List<Defect> findAllDefectDTOByReporterAndStatus(Person person, DefectStatusEnum statusEnum);

    List<Defect> findAllDefectDTOByAssigneeAndStatus(Person person, DefectStatusEnum statusEnum);


}
