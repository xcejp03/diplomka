package cz.vse.repository;

import cz.vse.entity.StatusEnum;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;
import cz.vse.entity.WorkTC;
import cz.vse.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface TCInstanceRepository extends BaseRepository<TCInstance> {
    List<TCInstance> findByTCMusterOrderById(TCMuster tcMuster);

    List<TCInstance> findByWorkTCOrderById(WorkTC workTC);

    TCInstance findTop1ByTCMusterOrderByCreatedDateTimeDesc(TCMuster tcMuster);

    @Query("select count(tci.id) from TCInstance tci WHERE tci.id in (select max(tci.id) from TCInstance tci join tci.tCMuster tcm WHERE tcm.project.id = :projectId " +
            "GROUP BY tci.tCMuster) and tci.status = :status")
    int getNumberOfTCsInProjectByStatus(@Param("status") StatusEnum status, @Param("projectId") long projectId);


}
