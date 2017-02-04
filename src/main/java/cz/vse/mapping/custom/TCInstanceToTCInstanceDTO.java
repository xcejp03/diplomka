package cz.vse.mapping.custom;

import cz.vse.dto.old.TCInstanceDTO;
import cz.vse.entity.*;
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
//        tcInstanceDTO.setStatus((getTCInstanceStatusFromTSInstancesStatuses(tcInstance)));
        super.mapAtoB(tcInstance, tcInstanceDTO, context);
    }

//    private StatusEnum getTCInstanceStatusFromTSInstancesStatuses(TCInstance tcInstance) {
//        List<TSInstance> tsInstanceList = tcInstance.getTsInstances();
//        List<StatusEnum> statusEnumList = new ArrayList<>();
//
//        for (TSInstance tsInstance : tsInstanceList) {
//            statusEnumList.add(tsInstance.getStatus());
//        }
//
//        if (statusEnumList.contains(StatusEnum.FAILED)) {
//            return StatusEnum.FAILED;
//        }
//        if (statusEnumList.contains(StatusEnum.BLOCKED)) {
//            return StatusEnum.BLOCKED;
//        }
//
//        if (isItAllNorun(statusEnumList)) {
//            return StatusEnum.NORUN;
//        }
//        if (isItAllPassed(statusEnumList)) {
//            return StatusEnum.PASSED;
//        }
//
//        return StatusEnum.NOTCOMPLETED;
//    }
//
//    private boolean isItAllPassed(List<StatusEnum> tsInstanceList) {
//        for (StatusEnum statusEnum : tsInstanceList) {
//            if (statusEnum != StatusEnum.PASSED) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean isItAllNorun(List<StatusEnum> tsInstanceList) {
//        for (StatusEnum statusEnum : tsInstanceList) {
//            if (statusEnum != StatusEnum.NORUN) {
//                return false;
//            }
//        }
//        return true;
//    }
}
