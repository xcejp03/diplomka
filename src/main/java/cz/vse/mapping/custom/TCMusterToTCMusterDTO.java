package cz.vse.mapping.custom;

import cz.vse.dto.TCMusterDTO;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;
import cz.vse.service.TCInstanceService;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 26.11.2016.
 */
@Component
public class TCMusterToTCMusterDTO extends CustomMapper<TCMuster, TCMusterDTO> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    TCInstanceService tcInstanceService;

    @Override
    public void mapAtoB(TCMuster tcMuster, TCMusterDTO tcMusterDTO, MappingContext context) {
        TCInstance tcInstance = tcInstanceService.findLastTCInstanceByTCMuster(tcMuster);
        super.mapAtoB(tcMuster, tcMusterDTO, context);
    }

}
