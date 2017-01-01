package cz.vse.entity;

import javax.persistence.Entity;

/**
 * Created by pcejka on 21.09.2016.
 */
//@Entity
public enum RoleEnum {
    ADMIN ("ADMIN"),
    TESTER ("TESTER"),
    ANALYTIC ("ANALYTIC");

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

/*
    Role udělat děděním z Person
 */
