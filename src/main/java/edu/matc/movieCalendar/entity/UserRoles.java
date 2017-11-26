package edu.matc.movieCalendar.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoles implements java.io.Serializable {

    @Column(name = "role_name", nullable = false, length = 15)
    private String roleName;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)
    private User user;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        //if (userName != null ? !userName.equals(userRoles.userName) : userRoles.userName != null) return false;
        if (roleName != null ? !roleName.equals(userRoles.roleName) : userRoles.roleName != null) return false;
        if (user != null ? !user.equals(userRoles.user) : userRoles.user != null) return false;

        return true;
    }
}
