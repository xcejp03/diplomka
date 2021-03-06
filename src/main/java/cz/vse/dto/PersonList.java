package cz.vse.dto;

import cz.vse.entity.RoleEnum;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */
public class PersonList extends BaseDTO {
    private String name;
    private String username;
    private String password;
    private String createdDate;
    private String lastLogged;
    private Boolean enabled;
    private List<RoleEnum> userRolesEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastLogged() {
        return lastLogged;
    }

    public void setLastLogged(String lastLogged) {
        this.lastLogged = lastLogged;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<RoleEnum> getUserRolesEnum() {
        return userRolesEnum;
    }

    public void setUserRolesEnum(List<RoleEnum> userRolesEnum) {
        this.userRolesEnum = userRolesEnum;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", login='" + username + '\'' +
                ", pass='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", lastLogged=" + lastLogged +
                '}';
    }
}

