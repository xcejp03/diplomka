package cz.vse.service;

import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;
import cz.vse.entity.TSInstance;
import cz.vse.entity.TSMuster;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TCService {

    TCInstanceRunDTO runNewTC(long tcMusterId);
    TCInstance mapTCMusterToTCInstance(TCMuster tcMuster);
    List<TSInstance> mapTSMusterToTSInstance(List<TSMuster> tsMusterList);
    TCInstance createAndSaveTCInstanceFromTCMusterId(long tcMusterId);
    List<TSInstance> createAndSaveTSInstanceFromTCMusterId(long tcMusterId, TCInstance tcInstance);
}
