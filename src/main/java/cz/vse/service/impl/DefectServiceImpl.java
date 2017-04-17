package cz.vse.service.impl;

import cz.vse.dto.DefectDTO;
import cz.vse.dto.DefectForm;
import cz.vse.dto.DefectList;
import cz.vse.entity.*;
import cz.vse.repository.DefectRepository;
import cz.vse.service.DefectCommentService;
import cz.vse.service.DefectService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Service
public class DefectServiceImpl implements DefectService {

    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    DefectRepository defectRepository;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private DefectCommentService defectCommentService;

    @Override
    public void createDefect(Defect defect) {
        l.info("with: " + defect);
        defect.setCreatedDateTime(LocalDateTime.now());
        defect.setStatus(DefectStatusEnum.open);
        defectRepository.save(defect);
        l.info("created: " + defect.toString());
    }

    @Override
    public void createDefect(DefectForm defectForm) {
        l.info("with: " + defectForm);
        Defect defect;
        defect = mapper.map(defectForm, Defect.class);
        defect.setCreatedDateTime(LocalDateTime.now());
        defect.setStatus(DefectStatusEnum.open);
        defectRepository.save(defect);
        l.info("created: " + defectForm.toString());
    }

    @Override
    public void createDefect(String description, PriorityDefectEnum priority, Person assignee,
                             Person reporter, DefectStatusEnum defectStatus, String affectVersion,
                             List<TCInstance> TCInstance, List<TSInstance> TSInstance) {
        l.info("with: " + description + " and " + priority + " and " + assignee + " and " + reporter +
                " and " + defectStatus + " and " + affectVersion + " and " + TCInstance + " and " + TCInstance);
        Defect defect = new Defect();
        defect.setDescription(description);
        defect.setPriority(priority);
        defect.setAssignee(assignee);
        defect.setReporter(reporter);
        defect.setStatus(defectStatus);
        defect.setAffectsVersion(affectVersion);
        defect.setTcInstances(TCInstance);
        defect.setTsInstances(TSInstance);
        defect.setCreatedDateTime(LocalDateTime.now());
        defect.setStatus(DefectStatusEnum.open);

        defectRepository.save(defect);
        l.info("created: " + defect.toString());
    }

    @Override
    public void updateDefect(DefectForm defectForm) {
        l.info("with: " + defectForm);
        Defect defect = defectRepository.findOne(defectForm.getId());
        mapper.map(defectForm, defect);
        defect.setUpdatedDateTime(LocalDateTime.now());
        defectRepository.save(defect);
        l.info("defect: " + defectForm.toString());
    }

    @Override
    public void updateDefect(DefectDTO defectDTO) {
        l.info("with: " + defectDTO);
        Defect defect = defectRepository.findOne(defectDTO.getId());
        mapper.map(defectDTO, defect);
        defect.setUpdatedDateTime(LocalDateTime.now());
        defectRepository.save(defect);
        l.info("defect: " + defectDTO.toString());
    }

    @Override
    public void deleteDefect(Defect defectToDelete) {
        l.info("with: " + defectToDelete);
        Long defectId = defectToDelete.getId();
        defectRepository.delete(defectToDelete);
        l.info("deleted: " + defectToDelete.toString());
    }

    @Override
    public void deleteDefect(long defectToDeleteId) {
        l.info("with: " + defectToDeleteId);
        defectRepository.delete(defectToDeleteId);
        l.info("deleted: " + defectToDeleteId);
    }

    @Override
    public Defect findDefect(long id) {
        l.info("with: " + id);
        Defect defect;
        defect = defectRepository.findOne(id);
        l.info("found: " + defect.toString());
        return defect;
    }

    @Override
    public DefectDTO findDefectDTO(long id) {
        l.info("with: " + id);
        Defect defect = defectRepository.findOne(id);
        DefectDTO defectDTO = mapper.map(defect, DefectDTO.class);
        l.info("found" + defectDTO.toString());
        return defectDTO;
    }

    @Override
    public DefectForm findDefectForm(long id) {
        l.info("with: " + id);
        Defect defect = defectRepository.findOne(id);
        DefectForm defectForm = mapper.map(defect, DefectForm.class);
        l.info("found" + defectForm.toString());
        return defectForm;
    }

    @Override
    public DefectList findDefectList(long id) {
        l.info("with: " + id);
        Defect defect = defectRepository.findOne(id);
        DefectList defectList = mapper.map(defect, DefectList.class);
        l.info("found" + defectList.toString());
        return defectList;
    }

    @Override
    public List<Defect> findAllDefects() {
        List<Defect> defectList = defectRepository.findAll();
        l.info("found: " + defectList.toString());
        return defectList;
    }

    @Override
    public List<DefectDTO> findAllDefectDTO() {
        List<Defect> defectList;
        List<DefectDTO> defectDTOList;
        defectList = defectRepository.findAll();
        defectDTOList = mapper.mapAsList(defectList, DefectDTO.class);
        l.info("found: " + defectDTOList);
        return defectDTOList;
    }

    @Override
    public List<DefectList> findAllDefectLists() {
        List<Defect> defectList;
        List<DefectList> defectLists;
        defectList = defectRepository.findAll();
        defectLists = mapper.mapAsList(defectList, DefectList.class);
        l.info("found: " + defectLists);
        return defectLists;
    }

    @Override
    public List<DefectDTO> findAllDefectDTOByReporterAndStatus(Person person, DefectStatusEnum statusEnum) {
        l.info("with: " + person + " and " + statusEnum);
        List<Defect> defectList;
        List<DefectDTO> defectDTOList;
        defectList = defectRepository.findAllDefectsByReporterAndStatus(person, statusEnum);
        defectDTOList = mapper.mapAsList(defectList, DefectDTO.class);
        l.info("found: " + defectDTOList);
        return defectDTOList;
    }

    @Override
    public List<DefectDTO> findAllDefectDTOByAssigneeAndStatus(Person person, DefectStatusEnum statusEnum) {
        l.info("with: " + person + " and " + statusEnum);
        List<Defect> defectList;
        List<DefectDTO> defectDTOList;
        defectList = defectRepository.findAllDefectsByAssigneeAndStatus(person, statusEnum);
        defectDTOList = mapper.mapAsList(defectList, DefectDTO.class);
        l.info("found: " + defectDTOList);
        return defectDTOList;
    }

    @Override
    public List<DefectList> findAllDefectListsByReporterAndStatus(Person person, DefectStatusEnum statusEnum) {
        l.info("with: " + person + " and " + statusEnum);
        List<Defect> defectList;
        List<DefectList> defectLists;
        defectList = defectRepository.findAllDefectsByReporterAndStatus(person, statusEnum);
        defectLists = mapper.mapAsList(defectList, DefectList.class);
        l.info("found: " + defectLists);
        return defectLists;
    }

    @Override
    public List<DefectList> findAllDefectListsByAssigneeAndStatus(Person person, DefectStatusEnum statusEnum) {
        l.info("with: " + person + " and " + statusEnum);
        List<Defect> defectList;
        List<DefectList> defectLists;
        defectList = defectRepository.findAllDefectsByAssigneeAndStatus(person, statusEnum);
        defectLists = mapper.mapAsList(defectList, DefectList.class);
        l.info("found: " + defectLists);
        return defectLists;
    }

    @Override
    public void changeDefectStatus(DefectForm defectForm, Person author) {
        l.info("with: " + defectForm + " and " + author);
        updateDefect(defectForm);
        defectCommentService.writeDefectStatusChange(defectForm, author);
    }

    @Override
    public void changeDefectStatus(DefectDTO defectDTO, Person author) {
        l.info("with: " + defectDTO + " and " + author);
        updateDefect(defectDTO);
        defectCommentService.writeDefectStatusChange(defectDTO, author);
    }

    @Override
    public void changeDefectAssignee(DefectDTO defectDTO, Person author) {
        l.info("with: " + defectDTO + " and " + author);
        updateDefect(defectDTO);
        defectCommentService.writeDefectAssigneeChange(defectDTO, author);
    }
}
