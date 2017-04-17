package cz.vse.mapping.converter;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LocalDateToLocalDateConverter extends BidirectionalConverter<LocalDate, LocalDate> {

    @Override
    public LocalDate convertTo(LocalDate source, Type<LocalDate> destinationType) {
        return source;
    }

    @Override
    public LocalDate convertFrom(LocalDate source, Type<LocalDate> destinationType) {
        return source;
    }
}


