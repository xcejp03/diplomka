package cz.vse.dto;

import java.time.LocalDateTime;

/**
 * Created by pcejka on 10.10.2016.
 */
public class PersonDTO extends BaseDTO {
    private String name;
    private String login;
    private String pass;
    private LocalDateTime createdDate;
    private LocalDateTime lastLogin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDTO)) return false;
        if (!super.equals(o)) return false;

        PersonDTO personDTO = (PersonDTO) o;

        if (getName() != null ? !getName().equals(personDTO.getName()) : personDTO.getName() != null) return false;
        if (getLogin() != null ? !getLogin().equals(personDTO.getLogin()) : personDTO.getLogin() != null) return false;
        if (getPass() != null ? !getPass().equals(personDTO.getPass()) : personDTO.getPass() != null) return false;
        if (getCreatedDate() != null ? !getCreatedDate().equals(personDTO.getCreatedDate()) : personDTO.getCreatedDate() != null)
            return false;
        return getLastLogin() != null ? getLastLogin().equals(personDTO.getLastLogin()) : personDTO.getLastLogin() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPass() != null ? getPass().hashCode() : 0);
        result = 31 * result + (getCreatedDate() != null ? getCreatedDate().hashCode() : 0);
        result = 31 * result + (getLastLogin() != null ? getLastLogin().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", createdDate=" + createdDate +
                ", lastLogin=" + lastLogin +
                '}';
    }
}

