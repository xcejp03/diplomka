package cz.vse.service.impl;

import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.dto.WorkListDTO;
import cz.vse.dto.WorkTCDTO;
import cz.vse.entity.Project;
import cz.vse.entity.WorkList;
import cz.vse.entity.WorkTC;
import cz.vse.repository.ProjectRepository;
import cz.vse.repository.WorkTCRepository;
import cz.vse.service.*;
import cz.vse.utils.HelpService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
@Transactional
public class WorkTCServiceImpl implements WorkTCService {
    @Autowired
    private WorkTCRepository workTCRepository;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private WorkListService workListService;


    @Override
    public void createWorkTC(WorkTC workTC) {
        workTC.setCreatedDateTime(LocalDateTime.now());
        workTCRepository.save(workTC);
    }

    @Override
    public void createWorkTC(WorkTCDTO workTCDTO) {
        WorkTC workTC;
        workTC = mapper.map(workTCDTO, WorkTC.class);
        workTC.setCreatedDateTime(LocalDateTime.now());
        workTCRepository.save(workTC);
    }

    @Override
    public void updateWorkTC(WorkTC workTC) {
        workTC.setUpdatedDateTime(LocalDateTime.now());
        workTCRepository.save(workTC);
    }

    @Override
    public void updateWorkTC(WorkTCDTO workTCDTO) {
        WorkTC workTC = findWorkTCById(workTCDTO.getId());
        mapper.map(workTCDTO, workTC);
        workTC.setUpdatedDateTime(LocalDateTime.now());
        workTCRepository.save(workTC);
    }

    @Override
    public void updateWorkTCEntity(List<WorkTC> workTCList) {
        List<WorkTC> workTCListUtil = new ArrayList<>();
        for (WorkTC workTC : workTCList) {
            WorkTC workTCEntity = findWorkTCById(workTC.getId());
            if (!workTC.equals(workTCEntity)) {
                mapper.map(workTC, workTCEntity);
                workTCEntity.setUpdatedDateTime(LocalDateTime.now());
            }
            workTCListUtil.add(workTCEntity);
        }
        workTCRepository.save(workTCListUtil);
    }

    @Override
    public void updateWorkTC(List<WorkTCDTO> workTCDTOList) {
        List<WorkTC> workTCList = new ArrayList<>();
        for (WorkTCDTO workTCDTO : workTCDTOList) {
            WorkTC workTC = findWorkTCById(workTCDTO.getId());
            mapper.map(workTCDTO, workTC);
            workTC.setUpdatedDateTime(LocalDateTime.now());
            workTCList.add(workTC);
        }
        workTCRepository.save(workTCList);
    }

    @Override
    public List<WorkTC> findAllWorkTC() {
        return workTCRepository.findAll();
    }

    @Override
    public List<WorkTCDTO> findAllWorkTCDTO() {
        List<WorkTC> workTCList;
        List<WorkTCDTO> workTCDTOList;
        workTCList = workTCRepository.findAll();
        workTCDTOList = mapper.mapAsList(workTCList, WorkTCDTO.class);
        return workTCDTOList;
    }

    @Override
    public WorkTC findWorkTCById(long id) {
        return workTCRepository.findOne(id);
    }

    @Override
    public WorkTCDTO findWorkTCDTOById(long id) {
        WorkTC workTC;
        WorkTCDTO workTCDTO;
        workTC = workTCRepository.findOne(id);
        workTCDTO = mapper.map(workTC, WorkTCDTO.class);
        return workTCDTO;
    }

    @Override
    public List<WorkTCDTO> findWorkTCDTOByWorkListId(long id) {
        WorkList workList = workListService.findWorkListById(id);
        List<WorkTCDTO> workTCDTOList = findWorkTCDTOByWorkList(workList);
        return workTCDTOList;
    }

    @Override
    public List<WorkTCDTO> findWorkTCDTOByWorkList(WorkList workList) {
        List<WorkTC> workTCList = workTCRepository.findWorkTCDTOByWorkList(workList);
        List<WorkTCDTO> workTCDTOList = mapper.mapAsList(workTCList, WorkTCDTO.class);
        return workTCDTOList;
    }

    @Override
    public List<WorkTC> findWorkTCByWorkListId(long id) {
        WorkList workList = workListService.findWorkListById(id);
        List<WorkTC> workTCList = workTCRepository.findWorkTCDTOByWorkList(workList);
        return workTCList;
    }
}