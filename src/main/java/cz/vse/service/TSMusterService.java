package cz.vse.service;

import cz.vse.dto.TSMusterDTO;
import cz.vse.entity.TSMuster;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TSMusterService {

    void createTestStepMuster(TSMusterDTO tsMusterDTO);

    void updateTestStepMuster(TSMusterDTO tsMusterDTO);

    void deleteTestStepMuster(TSMuster TSMusterToDelete);

    void deleteTestStepMuster(Long tsMusterId);

    TSMuster findTestStepMusterById(long id);

    TSMusterDTO findTestStepMusterDTOById(long id);

    List<TSMuster> findAllTestStepMusters();

    List<TSMusterDTO> findAllTestStepMustersDTO();

//    List<TSMuster> findAllTestStepMustersByTCMusterId(long tcMusterId);

}
