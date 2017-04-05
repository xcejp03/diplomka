package cz.vse.service;

import cz.vse.dto.WorkListDTO;
import cz.vse.dto.WorkListForm;
import cz.vse.dto.WorkListList;
import cz.vse.dto.WorkTCWrapper;
import cz.vse.entity.Person;
import cz.vse.entity.WorkList;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface WorkListService {
    void createWorkList(WorkList workList);

    void createWorkList(WorkListDTO workListDTO);

    void createWorkList(WorkListForm workListForm);

    void updateWorkList(WorkList workList);

    void updateWorkList(WorkListDTO workListDTO);

    void updateWorkList(WorkListForm workListForm);

    List<WorkList> findAllWorkList();

    List<WorkListDTO> findAllWorkListDTO();

    List<WorkListList> findAllWorkListListByAuthorId(long id);

    List<WorkListList> findAllWorkListListByMember(long id);

    WorkList findWorkListById(long id);

    WorkListDTO findWorkListDTOById(long id);

    WorkTCWrapper findWorkTCWrapperByWorklistId(long id);

    WorkListForm findWorkListFormById(long id);

    List<Long> getListTCMusterIdByWorklistInWorkTCDTO(long id);

    List<WorkListDTO> findAllWorkListDTOByMemberToday(Person person);

    List<WorkListDTO> findAllWorkListDTOByMemberTomorrow(Person person);

    List<WorkListDTO> findAllWorkListDTOByMemberLastThreeDays(Person person);

    void updateWorkTC(WorkTCWrapper workTCWrapper);
}
