package cz.vse.repository;

import cz.vse.entity.TCMuster;
import cz.vse.entity.TSMuster;
import cz.vse.repository.base.BaseRepository;

import java.util.List;

/**
 * Created by pcejka on 28.11.2016.
 */
public interface TSMusterRepository extends BaseRepository<TSMuster> {
    List<TSMuster> findAllTestStepMustersByTCMusterOrderById(TCMuster tcMuster);

    List<TSMuster> findAllTSMustersByTCMusterOrderById(TCMuster tcMuster);
}
