package cz.vse.mapping.custom;

import cz.vse.dto.TCMusterList;
import cz.vse.entity.StatusEnum;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TCMuster;
import cz.vse.service.TCInstanceService;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

/**
 * Created by pcejka on 26.11.2016.
 */
@Component
public class TCMusterToTCMusterList extends CustomMapper<TCMuster, TCMusterList> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    TCInstanceService tcInstanceService;

    @Override
    public void mapAtoB(TCMuster tcMuster, TCMusterList tcMusterList, MappingContext context) {
        TCInstance tcInstance = tcInstanceService.findLastTCInstanceByTCMuster(tcMuster);
        super.mapAtoB(tcMuster, tcMusterList, context);
        if (tcInstance != null) {
            tcMusterList.setStatus(tcInstance.getStatus());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy HH:mm");
            tcMusterList.setLastRunDateTime(tcInstance.getCreatedDateTime().format(formatter));
        } else {
            tcMusterList.setStatus(StatusEnum.NORUN);
        }

    }

}
