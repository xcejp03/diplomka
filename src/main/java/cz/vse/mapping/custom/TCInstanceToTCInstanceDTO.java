package cz.vse.mapping.custom;

import cz.vse.dto.TCInstanceDTO;
import cz.vse.entity.TCInstance;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 26.11.2016.
 */
@Component
public class TCInstanceToTCInstanceDTO extends CustomMapper<TCInstance, TCInstanceDTO> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Override
    public void mapAtoB(TCInstance tcInstance, TCInstanceDTO tcInstanceDTO, MappingContext context) {
        super.mapAtoB(tcInstance, tcInstanceDTO, context);
    }


}
