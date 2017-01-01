package cz.vse.mapping.converter;

import cz.vse.entity.TCInstance;
import cz.vse.entity.UserRole;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 02.12.2016.
 */
@Component
public class UserRoleToLongIdConverter extends AbstractEntityToIdConverter<UserRole, Long> {
}
