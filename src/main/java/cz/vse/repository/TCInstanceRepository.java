package cz.vse.repository;

import cz.vse.entity.*;
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

//
//    select count(tcinstance.id) from tcinstance WHERE id in (select max(tcinstance.id) from tcinstance join tcmuster on (tcinstance.tc_muster_id = tcmuster.id)
//    WHERE tcmuster.project_id ='20'
//    GROUP BY tc_muster_id) and tcinstance.status = '3';
//    @Query("select count (p.id) from Project p join p.tcMusters t where t.Author in :loggedPerson")
//    @Query("select count (p.id) from Project p join p.tcMusters t where t.Author in :loggedPerson")     //UKÁZKA JOIN

    @Query("select count(tci.id) from TCInstance tci WHERE tci.id in (select max(tci.id) from TCInstance tci join tci.tCMuster tcm WHERE tcm.project.id = :projectId " +
            "GROUP BY tci.tCMuster) and tci.status = :status")
    int getNumberOfTCsInProjectByStatus(@Param("status") StatusEnum status, @Param("projectId") long projectId);


}
