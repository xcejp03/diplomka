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
public class WorkTCToWorkTC extends CustomMapper<WorkTC, WorkTC> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Override
    public void mapAtoB(WorkTC workTC, WorkTC workTC2, MappingContext context) {
        l.info("A -> B");
        workTC2.setAssignee(workTC.getAssignee());
    }

    @Override
    public void mapBtoA(WorkTC workTC, WorkTC workTC2, MappingContext context) {
        l.info("B -> A");

    }


}
