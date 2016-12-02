package cz.vse.service.impl;

import cz.vse.dao.TestStepMusterDao;
import cz.vse.dto.TSMusterDTO;
import cz.vse.entity.TSMuster;
import cz.vse.repository.TSMusterRepository;
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
    TestStepMusterDao testStepMusterDao;
    @Autowired
    private MapperFacade mapper;

    public void createTestStepMuster(TSMusterDTO tsMusterDTO) {
        l.debug("creating TSMuster - service");
        TSMuster tsMuster = new TSMuster();
        tsMuster = mapper.map(tsMusterDTO, TSMuster.class);
        tsMuster.setCreatedDateTime(LocalDateTime.now());
        tsMusterRepository.save(tsMuster);
        l.info("created TSMuster - service: " + tsMusterDTO);
    }

    public void updateTestStepMuster(TSMusterDTO tsMusterDTO) {
        l.debug("updating TSMuster - service");
        TSMuster tsMuster;
        tsMuster = mapper.map(tsMusterDTO, TSMuster.class);
        tsMusterRepository.save(tsMuster);
        l.info("updated TSMuster - service: " + tsMuster);
    }

    public void deleteTestStepMuster(TSMuster TSMusterToDelete) {
        l.debug("deleting TSMuster - service");
        Long tsMusterId;
        tsMusterId = TSMusterToDelete.getId();
        tsMusterRepository.delete(tsMusterId);
        l.info("TSMuster deleted - service: " + tsMusterId);
    }

    public void deleteTestStepMuster(Long tsMusterId) {
        l.debug("deleting TSMuster - service");
        tsMusterRepository.delete(tsMusterId);
        l.info("TSMuster deleted - service: " + tsMusterId);
    }

    public TSMuster findTestStepMusterById(long id) {
        l.debug("finding TSMuster - service");
        TSMuster TSMuster;
        TSMuster = tsMusterRepository.findOne(id);
        l.info("TSMuster founf - service: " + id + " - " + TSMuster);
        return TSMuster;
    }

    public TSMusterDTO findTestStepMusterDTOById(long id) {
        l.debug("finding TSMuster - service");
        TSMuster tsMuster;
        TSMusterDTO tsMusterDTO;
        tsMuster = tsMusterRepository.findOne(id);
        tsMusterDTO = mapper.map(tsMuster, TSMusterDTO.class);
        l.info("TSMuster founf - service: " + id + " - " + tsMuster);
        return tsMusterDTO;
    }

    public List<TSMuster> findAllTestStepMusters() {
        l.debug("finding all testStepMusters - service");
        List<TSMuster> TSMusterList;
        TSMusterList = tsMusterRepository.findAll();
        l.info("found all testStepMusters - service: ");
        return TSMusterList;
    }

    public List<TSMusterDTO> findAllTestStepMustersDTO() {
        l.debug("finding all testStepMustersDTO - service");
        List<TSMuster> tsMusterList;
        List<TSMusterDTO> tsMusterDTOList;
        tsMusterList = tsMusterRepository.findAll();
        tsMusterDTOList = mapper.mapAsList(tsMusterList, TSMusterDTO.class);
        l.info("found all testStepMusters - service: ");
        return tsMusterDTOList;
    }

//    public List<TSMuster> findAllTestStepMustersByTCMusterId(long tcMusterId) {
//        List<TSMuster> tsMusters;
//        tsMusters = tsMusterRepository.findAllTestStepMustersByTCMusterId(tcMusterId);
//        return tsMusters;
//    }
}
