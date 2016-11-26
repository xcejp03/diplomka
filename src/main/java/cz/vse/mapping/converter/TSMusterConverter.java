package cz.vse.mapping.converter;

import cz.vse.entity.TSInstance;
import cz.vse.entity.TSMuster;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDateTime;

/**
 * Created by pcejka on 17.11.2016.
 */
public class TSMusterConverter extends CustomConverter<TSMuster, TSInstance> {

    @Override
    public TSInstance convert(TSMuster tsMuster, Type<? extends TSInstance> type) {
        TSInstance tsInstanceMapped = new TSInstance();
        tsInstanceMapped.setTsMuster(tsMuster);
        return tsInstanceMapped;
    }
}
