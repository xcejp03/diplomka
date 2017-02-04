package cz.vse.service;

import cz.vse.dto.TCMusterList;
import cz.vse.dto.old.TCMusterDTO;
import cz.vse.entity.*;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TCService {

    TCInstance runNewTC(long tcMusterId, Person person);
    TCInstance mapTCMusterToTCInstance(TCMuster tcMuster);
    List<TSInstance> mapTSMusterToTSInstance(List<TSMuster> tsMusterList);
//    TCInstance createAndSaveTCInstanceFromTCMusterId(long tcMusterId, Person person);
//    List<TSInstance> createAndSaveTSInstanceFromTCMusterId(long tcMusterId, TCInstance tcInstance);

    List<TCMusterDTO> findAllTCMustersDTO();
    List<TCMuster> findAllTCMusters();
    List<TCMusterDTO> findAllTCMustersDTOBySuiteId(Long id);
    List<TCMusterList> findAllTCMusterListsBySuiteId(Long id);
}
