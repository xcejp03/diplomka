package cz.vse.mapping.custom;

import cz.vse.dto.WorkListDTO;
import cz.vse.dto.WorkTCDTO;
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
public class WorkTCToWorkTCDTO extends CustomMapper<WorkTC, WorkTCDTO> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Override
    public void mapAtoB(WorkTC workTC, WorkTCDTO workTCDTO, MappingContext context) {
        super.mapAtoB(workTC, workTCDTO, context);
        workTCDTO.setName(workTC.getTcMuster().getName());
    }

    @Override
    public void mapBtoA(WorkTCDTO workTCDTO, WorkTC workTC, MappingContext context) {
        super.mapBtoA(workTCDTO, workTC, context);
    }


}
