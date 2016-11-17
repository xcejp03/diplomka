package cz.vse.mapping;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by pcejka on 17.11.2016.
 */
public class DateConverter extends CustomConverter<LocalDateTime, LocalDateTime> {

    @Override
    public LocalDateTime convert(LocalDateTime localDateTime, Type<? extends LocalDateTime> type) {


        return  (LocalDateTime.from(localDateTime));
    }
}
