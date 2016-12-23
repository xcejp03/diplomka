package cz.vse.entity;

/**
 * Created by pcejk on 20.12.2016.
 */


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserRole extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login")
    private Person user;
    private String role;

    public UserRole() {
    }

    public UserRole(Person user, String role) {
        this.user = user;
        this.role = role;
    }

    public Person getUser() {
        return this.user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    //        @Column(name = "role", length = 45)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}