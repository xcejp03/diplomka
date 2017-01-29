package cz.vse.logic;

import cz.vse.dto.TCMusterCopyDTO;
import cz.vse.dto.TCMusterDTO;
import cz.vse.entity.Project;
import cz.vse.entity.TCMuster;
import cz.vse.service.ProjectService;
import cz.vse.service.TCMusterService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejk on 29.01.2017.
 */
@Component
public class TCMusterLogic {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TCMusterService tcMusterService;
    @Autowired
    private MapperFacade mapper;

    public void copyTCMuster(TCMusterCopyDTO tcMusterCopyDTO) {
        l.warn("tcMusterCopyDTO: " + tcMusterCopyDTO);
        List<Long> tcMusterIDs = tcMusterCopyDTO.getTcMusters_id();
        Long targetProjectId = tcMusterCopyDTO.getProject_id();
        Project targetProject = projectService.findTestProjectById(targetProjectId);

        List<TCMuster> tcMusters = new ArrayList<>();

        for (Long tcMusterId : tcMusterIDs) {
//            TCMuster tcMuster = tcMusterService.findTestCaseMusterById(tcMusterId);
            TCMusterDTO tcMusterDTO = tcMusterService.findTestCaseMusterDTOById(tcMusterId);
            tcMusterDTO.setId(null);
            tcMusterDTO.setTcInstances_id(null);
            tcMusterDTO.setTestSuite_id(null);
            tcMusterDTO.setTsMusters_id(null);

            TCMuster tcMuster = mapper.map(tcMusterDTO, TCMuster.class);

            tcMuster.setProject(targetProject);
            tcMusters.add(tcMuster);

        }
        tcMusterService.createTestCaseMuster(tcMusters);

    }

}
