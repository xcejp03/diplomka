package cz.vse.mapping.converter;

import cz.vse.entity.Project;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 02.12.2016.
 */
@Component
public class ProjectToLongIdConverter extends AbstractEntityToIdConverter<Project,Long> {
}
