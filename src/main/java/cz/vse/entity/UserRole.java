package cz.vse.entity;

/**
 * Created by pcejk on 20.12.2016.
 */


import javax.persistence.*;

@Entity
public class UserRole extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login")
    private Person user;

    @Enumerated(EnumType.ORDINAL)
    private RoleEnum role;

    public UserRole() {
    }

    public UserRole(Person user, RoleEnum role) {
        this.user = user;
        this.role = role;
    }

    public Person getUser() {
        return this.user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public RoleEnum getRole() {
        return this.role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}