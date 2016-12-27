package cz.vse.service;

import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.dto.TCMusterDTO;
import cz.vse.entity.*;

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

    List<TCMusterDTO> findAllTCMustersDTO();
    List<TCMuster> findAllTCMusters();
    List<TCMusterDTO> findAllTCMustersDTOBySuiteId(Long id);
}
