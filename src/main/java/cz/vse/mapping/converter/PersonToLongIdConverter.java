package cz.vse.mapping.converter;

import cz.vse.entity.Person;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 02.12.2016.
 */
@Component
public class PersonToLongIdConverter extends AbstractEntityToIdConverter<Person, Long> {
}
