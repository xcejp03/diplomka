package cz.vse.entity;

/**
 * Created by pcejka on 21.09.2016.
 */
public enum RoleEnum {
    ADMIN("ADMIN"),
    TESTER("TESTER"),
    ANALYTIC("ANALYTIC"),
    MANAGER("MANAGER");

    private String roleString;

    RoleEnum(String roleString) {
        this.roleString = roleString;
    }

    @Override
    public String toString() {
        return roleString;
    }

    public String getRoleString() {
        return roleString;
    }

}
