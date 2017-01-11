package cz.vse.repository;

import cz.vse.entity.WorkList;
import cz.vse.entity.WorkTC;
import cz.vse.repository.base.BaseRepository;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface WorkTCRepository extends BaseRepository<WorkTC> {
    List<WorkTC> findWorkTCDTOByWorkList(WorkList workList);

}
