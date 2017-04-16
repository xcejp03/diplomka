package cz.vse.mapping.custom;

import cz.vse.entity.TCMuster;
import cz.vse.entity.TSInstance;
import cz.vse.entity.TSMuster;
import cz.vse.entity.WorkTC;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 26.11.2016.
 */
@Component
public class TCMusterToWorkTC extends CustomMapper<TCMuster, WorkTC> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Override
    public void mapAtoB(TCMuster tcMuster, WorkTC workTC, MappingContext context) {
        l.info("A -> B");
        super.mapAtoB(tcMuster, workTC, context);
    }

    @Override
    public void mapBtoA(WorkTC workTC, TCMuster tcMuster, MappingContext context) {
        l.info("B -> A");

        super.mapBtoA(workTC, tcMuster, context);
        l.info("B -> A - potom");

    }

}
