package cz.vse.mapping.converter;

import cz.vse.entity.Person;
import cz.vse.entity.TestSuite;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 02.12.2016.
 */
@Component
public class TestSuiteToLongIdConverter extends AbstractEntityToIdConverter<TestSuite, Long> {
}
