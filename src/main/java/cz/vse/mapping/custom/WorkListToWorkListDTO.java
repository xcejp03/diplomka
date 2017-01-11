package cz.vse.mapping.custom;

import cz.vse.dto.WorkListDTO;
import cz.vse.entity.TCMuster;
import cz.vse.entity.WorkList;
import cz.vse.entity.WorkTC;
import cz.vse.service.TCMusterService;
import cz.vse.service.WorkTCService;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 26.11.2016.
 */
@Component
public class WorkListToWorkListDTO extends CustomMapper<WorkList, WorkListDTO> {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private TCMusterService tcMusterService;

    @Autowired
    private WorkTCService workTCService;

    @Override
    public void mapAtoB(WorkList workList, WorkListDTO workListDTO, MappingContext context) {
        l.info("A -> B");
        super.mapAtoB(workList, workListDTO, context);
        workListDTO.setTcMuster_id(getTCMusterIdFromWorkTCDTO(workList));
    }

    @Override
    public void mapBtoA(WorkListDTO workListDTO, WorkList workList, MappingContext context) {
        super.mapBtoA(workListDTO, workList, context);
        List<WorkTC> workTCList = createWorkTCForWorkList(workList, workListDTO.getWorkTCList());
        workList.setWorkTCList(workTCList);
    }

    private List<Long> getTCMusterIdFromWorkTCDTO(WorkList workList) {
        List<WorkTC> workTCList = workList.getWorkTCList();
        List<Long> tcMusters_id = new ArrayList<>();
        for (WorkTC workTC : workTCList) {
            tcMusters_id.add(workTC.getTcMuster().getId());
        }
        return tcMusters_id;
    }

    private List<WorkTC> createWorkTCForWorkList(WorkList workList, List<Long> workTCListLong) {
        List<WorkTC> workTCList = new ArrayList<>();
        for (Long tcMusterId : workTCListLong) {
            WorkTC workTC = new WorkTC();
            TCMuster tcMuster = tcMusterService.findTestCaseMusterById(tcMusterId);
            workTC.setCreatedDateTime(LocalDateTime.now());
            workTC.setPriority(tcMuster.getPriority());
            workTC.setTcMuster(tcMuster);
            workTC.setWorkList(workList);
            workTCList.add(workTC);
            workTCService.createWorkTC(workTC);
            l.info("vytvořeno i " + workTC);
        }

        return workTCList;
    }

}
