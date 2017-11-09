package edu.matc.movieCalendar.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoles {
    private String userName;
    private String roleName;
    private User userByUserName;

    @Id
    @Column(name = "user_name", nullable = false, length = 15)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "role_name", nullable = false, length = 15)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        if (userName != null ? !userName.equals(userRoles.userName) : userRoles.userName != null) return false;
        if (roleName != null ? !roleName.equals(userRoles.roleName) : userRoles.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    /**
    @OneToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)
    public User getUserByUserName() {
        return userByUserName;
    }

    public void setUserByUserName(User userByUserName) {
        this.userByUserName = userByUserName;
     } */
}
