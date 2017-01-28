package cz.vse.repository;

import cz.vse.entity.Person;
import cz.vse.entity.WorkList;
import cz.vse.entity.WorkTC;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface WorkTCRepository extends BaseRepository<WorkTC> {
    List<WorkTC> findWorkTCDTOByWorkList(WorkList workList);


    @Query("select w from WorkTC w full join w.tcRunHistory h where w.assignee = :assignee and h.workTC is null ")
    List<WorkTC> getMyOpenWorkTC(@Param("assignee") Person assignee);

    @Query("select count(w.id) from WorkTC w full join w.tcRunHistory h where w.assignee = :assignee and h.workTC is null ")
    int getMyOpenWorkTCCount(@Param("assignee") Person assignee);
}
