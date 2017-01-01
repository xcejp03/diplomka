package cz.vse.mapping.custom;

        import cz.vse.entity.RoleEnum;
        import cz.vse.entity.TSInstance;
        import cz.vse.entity.TSMuster;
        import cz.vse.entity.UserRole;
        import ma.glasnost.orika.CustomMapper;
        import ma.glasnost.orika.MappingContext;
        import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 26.11.2016.
 */
@Component
public class RoleEnumToUserRole extends CustomMapper<RoleEnum, UserRole> {
    @Override
    public void mapAtoB(RoleEnum roleEnum, UserRole userRole, MappingContext context) {
        userRole.setRole(roleEnum);
        super.mapAtoB(roleEnum, userRole, context);
    }

    @Override
    public void mapBtoA(UserRole userRole, RoleEnum roleEnum, MappingContext context) {
        roleEnum = userRole.getRole();
        super.mapBtoA(userRole, roleEnum, context);
    }
}
