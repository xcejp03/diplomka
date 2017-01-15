package cz.vse.service;

import cz.vse.dto.WorkTCDTO;
import cz.vse.entity.*;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface WorkTCService {
    void createWorkTC(WorkTC workTC);

    void createWorkTC(WorkTCDTO workTCDTO);

    void updateWorkTC(WorkTC workTC);

    void updateWorkTC(WorkTCDTO workTCDTO);

    void updateWorkTCEntity(List<WorkTC> workTCList);

    void updateWorkTC(List<WorkTCDTO> workTCDTOList);

    List<WorkTC> findAllWorkTC();

    List<WorkTCDTO> findAllWorkTCDTO();

    WorkTC findWorkTCById(long id);

    WorkTCDTO findWorkTCDTOById(long id);

    List<WorkTCDTO> findWorkTCDTOByWorkListId(long id);

    List<WorkTCDTO> findWorkTCDTOByWorkList(WorkList workList);

    List<WorkTC> findWorkTCByWorkListId(long id);

    List<WorkTCDTO> findAllWorkTCDTOByWorkListId(long id);
}
