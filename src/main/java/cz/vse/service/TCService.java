package cz.vse.service;

import cz.vse.dto.TCMusterDTO;
import cz.vse.dto.TCMusterList;
import cz.vse.entity.*;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TCService {

    TCInstance runNewTC(long tcMusterId, Person person, String backId, String instanceOrigin);

    TCInstance mapTCMusterToTCInstance(TCMuster tcMuster);

    List<TSInstance> mapTSMusterToTSInstance(List<TSMuster> tsMusterList);

    List<TCMusterDTO> findAllTCMustersDTO();

    List<TCMuster> findAllTCMusters();

    List<TCMusterDTO> findAllTCMustersDTOBySuiteId(Long id);

    List<TCMusterList> findAllTCMusterListsBySuiteId(Long id);
}
