package cz.vse.repository;

import cz.vse.entity.Person;
import cz.vse.entity.WorkList;
import cz.vse.entity.WorkTC;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface WorkTCRepository extends BaseRepository<WorkTC> {
    List<WorkTC> findWorkTCDTOByWorkList(WorkList workList);


//    @Query("select w from WorkTC w where ")
//    List<WorkTC> getMyOpenWorkTC (Person person);

}
