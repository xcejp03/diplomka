package cz.vse.repository;

import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;
import cz.vse.repository.base.BaseRepository;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface TCInstanceRepository extends BaseRepository<TCInstance> {
    List<TCInstance> findByTCMusterOrderById(TCMuster tcMuster);
    TCInstance findTop1ByTCMusterOrderByIdDesc(TCMuster tcMuster);
}
