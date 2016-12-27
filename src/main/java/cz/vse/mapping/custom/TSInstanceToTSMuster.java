package cz.vse.mapping.custom;

import cz.vse.entity.TSInstance;
import cz.vse.entity.TSMuster;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 26.11.2016.
 */
@Component
public class TSInstanceToTSMuster extends CustomMapper<TSInstance, TSMuster> {

    @Override
    public void mapBtoA(TSMuster tsMuster, TSInstance tsInstance, MappingContext context) {
        tsInstance.setTsMuster(tsMuster);
        super.mapBtoA(tsMuster, tsInstance, context);
    }
}
