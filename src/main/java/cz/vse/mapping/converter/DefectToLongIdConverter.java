package cz.vse.mapping.converter;

import cz.vse.entity.Defect;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 02.12.2016.
 */
@Component
public class DefectToLongIdConverter extends AbstractEntityToIdConverter<Defect, Long> {
}
