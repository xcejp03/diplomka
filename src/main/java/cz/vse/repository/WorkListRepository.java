package cz.vse.repository;

import cz.vse.dto.WorkListDTO;
import cz.vse.entity.*;
import cz.vse.repository.base.BaseRepository;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface WorkListRepository extends BaseRepository<WorkList> {
    List<WorkList> findAllWorkListDTOByAuthor(Person person);
    List<WorkList> findAllWorkListDTOByProjectIn(List<Project> projects);

}
