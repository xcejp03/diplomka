package cz.vse.mapping.custom;

import cz.vse.dto.WorkListForm;
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
public class WorkListToWorkListForm extends CustomMapper<WorkList, WorkListForm> {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    private TCMusterService tcMusterService;

    @Autowired
    private WorkTCService workTCService;

//    @Override
//    public void mapAtoB(WorkList workList, WorkListDTO workListDTO, MappingContext context) {
//        l.info("A -> B");
//        super.mapAtoB(workList, workListDTO, context);
//        workListDTO.setTcMuster_id(getTCMusterIdFromWorkTCDTO(workList));
//    }


    @Override
    public void mapAtoB(WorkList workList, WorkListForm workListForm, MappingContext context) {


    }

    @Override
    public void mapBtoA(WorkListForm workListForm, WorkList workList, MappingContext context) {
        l.info("B -> A");
//        super.mapBtoA(workListDTO, workList, context);

//        if (workList.getWorkTCList() == null) {
        List<WorkTC> workTCList = createWorkTCForWorkList(workList, workListForm.getTcMuster_id());
        workList.setWorkTCList(workTCList);
//        }
    }

    private List<Long> getTCMusterIdFromWorkTCDTO(WorkList workList) {
        List<WorkTC> workTCList = workList.getWorkTCList();
        List<Long> tcMusters_id = new ArrayList<>();
        for (WorkTC workTC : workTCList) {
            tcMusters_id.add(workTC.getTcMuster().getId());
        }
        return tcMusters_id;
    }

    private List<WorkTC> createWorkTCForWorkList(WorkList workList, List<Long> tcMusterIdList) {
        List<WorkTC> workTCList = new ArrayList<>();
        List<Long> tcMusterIdListOld = new ArrayList<>();
        if (workList.getWorkTCList() == null) {
            l.info("(workList.getWorkTCList() je NULL");
        } else {
            l.info("(workList.getWorkTCList() není null");
            workTCList.addAll(workList.getWorkTCList());
            for (WorkTC workTC : workList.getWorkTCList()) {
                tcMusterIdListOld.add(workTCService.findWorkTCById(workTC.getId()).getTcMuster().getId());
//            tcMusterIdListOld.add(workTC.getTcMuster().getId());
            }
        }


        for (Long tcMusterId : tcMusterIdList) {
            if (!tcMusterIdListOld.contains(tcMusterId)) {
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
        }

        return workTCList;
    }

}
