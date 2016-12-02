package cz.vse.mapping.converter;

import cz.vse.entity.TestSuite;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 28.11.2016.
 */
@Component
public class TestSuiteToLongIdConverter extends AbstractEntityToIdConverter<TestSuite, Long> {
}
