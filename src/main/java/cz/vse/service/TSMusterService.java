package cz.vse.service;

import cz.vse.dto.TSMusterDTO;
import cz.vse.dto.TSMusterForm;
import cz.vse.dto.TSMusterList;
import cz.vse.entity.TCMuster;
import cz.vse.entity.TSMuster;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface TSMusterService {

    void createTestStepMuster(TSMusterDTO tsMusterDTO);
    void createTestStepMuster(TSMusterForm tsMusterForm);

    void updateTestStepMuster(TSMusterDTO tsMusterDTO);
    void updateTestStepMuster(TSMusterForm tsMusterForm);

    void deleteTestStepMuster(TSMuster TSMusterToDelete);

    void deleteTestStepMuster(Long tsMusterId);

    TSMuster findTestStepMusterById(long id);

    TSMusterDTO findTestStepMusterDTOById(long id);

    TSMusterForm findTestStepMusterFormById(long id);

    List<TSMuster> findAllTestStepMusters();

    List<TSMusterDTO> findAllTestStepMustersDTO();

    List<TSMusterForm> findAllTestStepMusterForms();

    List<TSMuster> findAllTestStepMustersByTCMuster (TCMuster tcMuster);

    List<TSMusterDTO> findAllTSMustersDTOByTCMusterId(Long id);

    List<TSMusterForm> findAllTSMusterFormsByTCMusterId(Long id);

    List<TSMusterList> findAllTSMusterListsByTCMusterId(Long id);

    List<TSMuster>findAllTSMustersByTCMuster (TCMuster tcMuster);
}
