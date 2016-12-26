package cz.vse.dto;

import java.time.LocalDateTime;

/**
 * Created by pcejka on 10.10.2016.
 */
public class PersonDTO extends BaseDTO {
    private String name;
    private String username;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime lastLogin;
    private Boolean enabled;

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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", login='" + username + '\'' +
                ", pass='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", lastLogin=" + lastLogin +
                '}';
    }
}

