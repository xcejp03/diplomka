package cz.vse.repository;

import cz.vse.entity.*;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface WorkListRepository extends BaseRepository<WorkList> {
    List<WorkList> findAllWorkListDTOByAuthor(Person person);
    List<WorkList> findAllWorkListDTOByProjectIn(List<Project> projects);

    @Query("select w from WorkList w where w.name = 'sda'")
    public WorkList findTestXXX();

}
