package cz.vse.repository;

import cz.vse.entity.TCInstance;
import cz.vse.entity.TSInstance;
import cz.vse.repository.base.BaseRepository;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface TSInstanceRepository extends BaseRepository<TSInstance> {
    List<TSInstance> findAllTestStepInstancesByTCInstanceOrderById(TCInstance tcInstance);
}
