package cz.vse.service.impl;

import cz.vse.dto.DefectDTO;
import cz.vse.entity.*;
import cz.vse.repository.DefectRepository;
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

    public void createDefect(Defect defect) {
        l.debug("creating defect - service");
        defect.setCreatedDateTime(LocalDateTime.now());
        defect.setStatus(DefectStatusEnum.open);
        defectRepository.save(defect);
        l.info("defect created - service: " + defect.toString());
    }

    public void createDefect(DefectDTO defectDTO) {
        l.debug("creating defect - service");
        Defect defect;
        defect = mapper.map(defectDTO, Defect.class);
        defect.setCreatedDateTime(LocalDateTime.now());
        defect.setStatus(DefectStatusEnum.open);
        defectRepository.save(defect);
        l.info("defect created - service: " + defectDTO.toString());
    }

    public void createDefect(String description, PriorityDefectEnum priority, Person assignee,
                             Person reporter, DefectStatusEnum defectStatus, String affectVersion,
                             List<TCInstance> TCInstance, List<TSInstance> TSInstance) {
        l.debug("creating defect - service ");
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
        l.info("defect created - service: " + defect.toString());
    }

    public void updateDefect(DefectDTO defectDTO) {
        l.debug("updating defect - service");
        Defect defect = defectRepository.findOne(defectDTO.getId());
        mapper.map(defectDTO, defect);
        defect.setUpdatedDateTime(LocalDateTime.now());
        defectRepository.save(defect);
        l.info("updating defect - service: " + defectDTO.toString());
    }

    public void deleteDefect(Defect defectToDelete) {
        l.debug("deleting defect - service");
        Long defectId = defectToDelete.getId();
        defectRepository.delete(defectToDelete);
        l.info("defect deleted - service: " + defectToDelete.toString());
    }

    public void deleteDefect(long defectToDeleteId) {
        l.debug("deleting defect - service");
        defectRepository.delete(defectToDeleteId);
        l.info("defect deleted - service: " + defectToDeleteId);
    }

    public Defect findDefectById(long id) {
        l.debug("finding defect by id - service");
        Defect defect;
        defect = defectRepository.findOne(id);
        l.info("found defect by id - service: " + id + " - " + defect.toString());
        return defect;
    }

    public DefectDTO findDefectDTOById(long id) {
        l.debug("finding defect by id - service");
        Defect defect = defectRepository.findOne(id);
        DefectDTO defectDTO = mapper.map(defect, DefectDTO.class);
        l.info("found defect by id - service: " + id + " - " + defectDTO.toString());
        return defectDTO;
    }

    public List<Defect> findAllDefects() {
        l.debug("finding all defects - service");
        List<Defect> defectList = defectRepository.findAll();
        l.info("found all defects - service: " + defectList.toString());
        return defectList;
    }

    @Override
    public List<DefectDTO> findAllDefectDTO() {
        l.debug("finding defect by id - service");
        List<Defect> defectList;
        List<DefectDTO> defectDTOList;
        defectList = defectRepository.findAll();
        defectDTOList = mapper.mapAsList(defectList, DefectDTO.class);

        return defectDTOList;
    }

    @Override
    public List<DefectDTO> findAllDefectDTOByReporter(Person person) {
        List<Defect> defectList;
        List<DefectDTO> defectDTOList;
        defectList = defectRepository.findAllDefectDTOByReporter(person);
        defectDTOList = mapper.mapAsList(defectList, DefectDTO.class);
        return defectDTOList;
    }

    @Override
    public List<DefectDTO> findAllDefectDTOByAssignee(Person person) {
        List<Defect> defectList;
        List<DefectDTO> defectDTOList;
        defectList = defectRepository.findAllDefectDTOByAssignee(person);
        defectDTOList = mapper.mapAsList(defectList, DefectDTO.class);
        return defectDTOList;
    }
}
