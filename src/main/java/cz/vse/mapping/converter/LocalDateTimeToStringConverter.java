package cz.vse.mapping.converter;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class LocalDateTimeToStringConverter extends BidirectionalConverter<LocalDateTime, String> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Override
    public String convertTo(LocalDateTime localDateTime, Type<String> type) {
        l.info("convertTo");
        l.info("data: " + localDateTime + "->" + type);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy HH:mm");
        String formattedLocalDate = localDateTime.format(formatter);
        l.info("formattedLocalDate: " + formattedLocalDate);
        return formattedLocalDate;
    }

    @Override
    public LocalDateTime convertFrom(String s, Type<LocalDateTime> type) {
        l.info("convertFrom");
        l.info("data: " + s + "->" + type);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy HH:mm");
        formatter = formatter.withLocale(Locale.UK);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDateTime date = LocalDateTime.parse(s, formatter);
        l.info(date);
        l.info("converted date: " + date);
        return date;
    }
}


