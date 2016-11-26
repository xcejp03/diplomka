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
public class TSMusterToTSInstance extends CustomMapper<TSMuster, TSInstance>{
    @Override
    public void mapAtoB(TSMuster tsMuster, TSInstance tsInstance, MappingContext context) {
        tsInstance.setTsMuster(tsMuster);
    }


}
