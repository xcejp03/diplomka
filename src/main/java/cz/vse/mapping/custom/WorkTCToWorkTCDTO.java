package cz.vse.mapping.custom;

import cz.vse.dto.WorkTCDTO;
import cz.vse.entity.WorkTC;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 26.11.2016.
 */
@Component
public class WorkTCToWorkTCDTO extends CustomMapper<WorkTC, WorkTCDTO> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Override
    public void mapAtoB(WorkTC workTC, WorkTCDTO workTCDTO, MappingContext context) {
        l.info("WorkTCToWorkTCDTO: A -> B");
//        super.mapAtoB(workTC, workTCDTO, context);
        workTCDTO.setName(workTC.getTcMuster().getName());
    }

    @Override
    public void mapBtoA(WorkTCDTO workTCDTO, WorkTC workTC, MappingContext context) {
        l.info("WorkTCToWorkTCDTO: B -> A");
//        super.mapBtoA(workTCDTO, workTC, context);
    }


}
