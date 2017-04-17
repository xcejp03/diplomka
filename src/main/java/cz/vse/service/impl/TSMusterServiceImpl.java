package cz.vse.service.impl;

import cz.vse.dto.TSMusterDTO;
import cz.vse.dto.TSMusterForm;
import cz.vse.dto.TSMusterList;
import cz.vse.entity.TCMuster;
import cz.vse.entity.TSMuster;
import cz.vse.repository.TSMusterRepository;
import cz.vse.service.TCMusterService;
import cz.vse.service.TSMusterService;
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
public class TSMusterServiceImpl implements TSMusterService {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    private TSMusterRepository tsMusterRepository;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private TCMusterService tcMusterService;

    public void createTestStepMuster(TSMusterDTO tsMusterDTO) {
        l.info("with: " + tsMusterDTO);
        TSMuster tsMuster = new TSMuster();
        tsMuster = mapper.map(tsMusterDTO, TSMuster.class);
        tsMuster.setCreatedDateTime(LocalDateTime.now());
        tsMusterRepository.save(tsMuster);
        l.info("created: " + tsMusterDTO);
    }

    @Override
    public void createTestStepMuster(TSMusterForm tsMusterForm) {
        l.info("with: " + tsMusterForm);
        TSMuster tsMuster = mapper.map(tsMusterForm, TSMuster.class);
        tsMuster.setCreatedDateTime(LocalDateTime.now());
        tsMusterRepository.save(tsMuster);
        l.info("created: " + tsMuster);
    }

    public void updateTestStepMuster(TSMusterDTO tsMusterDTO) {
        l.info("with: " + tsMusterDTO);
        TSMuster tsMuster = tsMusterRepository.findOne(tsMusterDTO.getId());
        mapper.map(tsMusterDTO, tsMuster);
        tsMuster.setUpdatedDateTime(LocalDateTime.now());
        tsMusterRepository.save(tsMuster);
        l.info("updated: " + tsMuster);
    }

    @Override
    public void updateTestStepMuster(TSMusterForm tsMusterForm) {
        l.info("with: " + tsMusterForm);
        TSMuster tsMuster = tsMusterRepository.findOne(tsMusterForm.getId());
        mapper.map(tsMusterForm, tsMuster);
        tsMuster.setUpdatedDateTime(LocalDateTime.now());
        tsMusterRepository.save(tsMuster);
        l.info("updated: " + tsMuster);
    }

    public void deleteTestStepMuster(TSMuster tsMusterToDelete) {
        l.info("with: " + tsMusterToDelete);
        Long tsMusterId;
        tsMusterId = tsMusterToDelete.getId();
        tsMusterRepository.delete(tsMusterId);
        l.info("deleted: " + tsMusterId);
    }

    public void deleteTestStepMuster(Long tsMusterId) {
        l.info("with: " + tsMusterId);
        tsMusterRepository.delete(tsMusterId);
        l.info("deleted: " + tsMusterId);
    }

    public TSMuster findTestStepMusterById(long id) {
        l.info("with: " + id);
        TSMuster tsMuster;
        tsMuster = tsMusterRepository.findOne(id);
        l.info("found: " + tsMuster);
        return tsMuster;
    }

    public TSMusterDTO findTestStepMusterDTOById(long id) {
        l.info("with: " + id);
        TSMuster tsMuster;
        TSMusterDTO tsMusterDTO;
        tsMuster = tsMusterRepository.findOne(id);
        tsMusterDTO = mapper.map(tsMuster, TSMusterDTO.class);
        l.info("found: " + tsMusterDTO);
        return tsMusterDTO;
    }

    @Override
    public TSMusterForm findTestStepMusterFormById(long id) {
        l.info("with: " + id);
        TSMuster tsMuster;
        TSMusterForm tsMusterForm;
        tsMuster = tsMusterRepository.findOne(id);
        tsMusterForm = mapper.map(tsMuster, TSMusterForm.class);
        l.info("found: " + tsMusterForm);
        return tsMusterForm;
    }


    public List<TSMuster> findAllTestStepMusters() {
        List<TSMuster> tsMusterList;
        tsMusterList = tsMusterRepository.findAll();
        l.info("found: " + tsMusterList);
        return tsMusterList;
    }

    public List<TSMusterDTO> findAllTestStepMustersDTO() {
        List<TSMuster> tsMusterList;
        List<TSMusterDTO> tsMusterDTOList;
        tsMusterList = tsMusterRepository.findAll();
        tsMusterDTOList = mapper.mapAsList(tsMusterList, TSMusterDTO.class);
        l.info("found: " + tsMusterDTOList);
        return tsMusterDTOList;
    }

    @Override
    public List<TSMusterForm> findAllTestStepMusterForms() {
        List<TSMuster> tsMusterList;
        List<TSMusterForm> tsMusterForms;
        tsMusterList = tsMusterRepository.findAll();
        tsMusterForms = mapper.mapAsList(tsMusterList, TSMusterForm.class);
        l.info("found: " + tsMusterForms);
        return tsMusterForms;
    }

    public List<TSMuster> findAllTestStepMustersByTCMuster(TCMuster tcMuster) {
        l.info("with: " + tcMuster);
        List<TSMuster> tsMusters;
        tsMusters = tsMusterRepository.findAllTestStepMustersByTCMusterOrderById(tcMuster);
        l.info("found: " + tsMusters);
        return tsMusters;
    }

    @Override
    public List<TSMuster> findAllTSMustersByTCMuster(TCMuster tcMuster) {
        l.info("with: " + tcMuster);
        List<TSMuster> tsMusterList = tsMusterRepository.findAllTSMustersByTCMusterOrderById(tcMuster);
        l.info("found: " + tsMusterList);
        return tsMusterList;
    }

    @Override
    public List<TSMusterDTO> findAllTSMustersDTOByTCMusterId(Long id) {
        l.info("with: " + id);
        List<TSMusterDTO> tsMusterDTOList;
        List<TSMuster> tsMusterList;
        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        tsMusterList = tsMusterRepository.findAllTSMustersByTCMusterOrderById(tcMuster);
        tsMusterDTOList = mapper.mapAsList(tsMusterList, TSMusterDTO.class);
        l.info("found: " + tsMusterDTOList);
        return tsMusterDTOList;
    }

    @Override
    public List<TSMusterForm> findAllTSMusterFormsByTCMusterId(Long id) {
        l.info("with: " + id);
        List<TSMusterForm> tsMusterForms;
        List<TSMuster> tsMusterList;
        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        tsMusterList = tsMusterRepository.findAllTSMustersByTCMusterOrderById(tcMuster);
        tsMusterForms = mapper.mapAsList(tsMusterList, TSMusterForm.class);
        l.info("found: " + tsMusterForms);
        return tsMusterForms;
    }

    @Override
    public List<TSMusterList> findAllTSMusterListsByTCMusterId(Long id) {
        l.info("with: " + id);
        List<TSMusterList> tsMusterLists;
        List<TSMuster> tsMusterList;
        TCMuster tcMuster = tcMusterService.findTestCaseMusterById(id);
        tsMusterList = tsMusterRepository.findAllTSMustersByTCMusterOrderById(tcMuster);
        tsMusterLists = mapper.mapAsList(tsMusterList, TSMusterList.class);
        l.info("found: " + tsMusterLists);
        return tsMusterLists;
    }
}
