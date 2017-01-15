package cz.vse.service.impl;

import cz.vse.dto.WorkListDTO;
import cz.vse.dto.WorkTCDTO;
import cz.vse.entity.Person;
import cz.vse.entity.WorkList;
import cz.vse.entity.WorkTC;
import cz.vse.repository.WorkListRepository;
import cz.vse.repository.WorkTCRepository;
import cz.vse.service.PersonService;
import cz.vse.service.WorkListService;
import cz.vse.service.WorkTCService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
@Transactional
public class WorkListServiceImpl implements WorkListService {
    @Autowired
    private MapperFacade mapper;

    @Autowired
    private WorkListRepository workListRepository;

    @Autowired
    private WorkTCService workTCService;

    @Autowired
    private PersonService personService;

    @Override
    public void createWorkList(WorkList workList) {
        workList.setCreatedDateTime(LocalDateTime.now());
        workListRepository.save(workList);
    }

    @Override
    public void createWorkList(WorkListDTO workListDTO) {
        WorkList workList;
        workList = mapper.map(workListDTO, WorkList.class);
        workList.setCreatedDateTime(LocalDateTime.now());
        workListRepository.save(workList);
    }

    @Override
    public void updateWorkList(WorkList workList) {
        workList.setUpdatedDateTime(LocalDateTime.now());
        workListRepository.save(workList);
    }

    @Override
    public void updateWorkList(WorkListDTO workListDTO) {
        WorkList workList = findWorkListById(workListDTO.getId());
        mapper.map(workListDTO, workList);
        workList.setUpdatedDateTime(LocalDateTime.now());
        workTCService.updateWorkTCEntity(workList.getWorkTCList());
        workListRepository.save(workList);

//        workTCService.updateWorkTCEntity();
    }

    @Override
    public List<WorkList> findAllWorkList() {
        return workListRepository.findAll();
    }

    @Override
    public List<WorkListDTO> findAllWorkListDTO() {
        List<WorkList> workListList = workListRepository.findAll();
        List<WorkListDTO> workListDTOList = mapper.mapAsList(workListList, WorkListDTO.class);
        return workListDTOList;
    }

    @Override
    public WorkList findWorkListById(long id) {
        return workListRepository.findOne(id);
    }

    @Override
    public WorkListDTO findWorkListDTOById(long id) {
        WorkList workList = workListRepository.findOne(id);
        WorkListDTO workListDTO = mapper.map(workList, WorkListDTO.class);
        return workListDTO;
    }

    @Override
    public List<WorkListDTO> findAllWorkListDTOByAuthorId(long id) {
        Person author = personService.findPersonById(id);
        List<WorkListDTO> workListDTOList;
        List<WorkList> workListList = workListRepository.findAllWorkListDTOByAuthor(author);
        workListDTOList = mapper.mapAsList(workListList, WorkListDTO.class);
        return workListDTOList;
    }

    @Override
    public List<WorkListDTO> findAllWorkListDTOByMember(long id) {
        Person member = personService.findPersonById(id);

        List<WorkListDTO> workListDTOList;
        List<WorkList> workListList = workListRepository.findAllWorkListDTOByProjectIn(member.getProjectsMember());
        workListDTOList = mapper.mapAsList(workListList, WorkListDTO.class);
        return workListDTOList;
    }

    @Override
    public List<Long> getListTCMusterIdByWorklistInWorkTCDTO(long id) {
        List<WorkTCDTO> workTCDTOList = workTCService.findAllWorkTCDTOByWorkListId(id);
        List<Long> listTCMusterIdByWorklistInWorkTCDTO = new ArrayList<>();
        for (WorkTCDTO workTCDTO : workTCDTOList) {
            Long tcMuster_id = workTCDTO.getTcMuster_id();
            listTCMusterIdByWorklistInWorkTCDTO.add(tcMuster_id);
        }
        return listTCMusterIdByWorklistInWorkTCDTO;
    }
}