package cz.vse.service;

import cz.vse.dto.WorkListDTO;
import cz.vse.entity.WorkList;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface WorkListService {
    void createWorkList(WorkList workList);

    void createWorkList(WorkListDTO workListDTO);

    void updateWorkList(WorkList workList);

    void updateWorkList(WorkListDTO workListDTO);

    List<WorkList> findAllWorkList();

    List<WorkListDTO> findAllWorkListDTO();

    WorkList findWorkListById(long id);

    WorkListDTO findWorkListDTOById(long id);

}
