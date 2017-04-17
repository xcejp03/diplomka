package cz.vse.mapping.converter;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalDateTimeToLocalDateTimeConverter extends BidirectionalConverter<LocalDateTime, LocalDateTime> {

    @Override
    public LocalDateTime convertTo(LocalDateTime source, Type<LocalDateTime> destinationType) {
        return source;
    }

    @Override
    public LocalDateTime convertFrom(LocalDateTime source, Type<LocalDateTime> destinationType) {
        return source;
    }
}


