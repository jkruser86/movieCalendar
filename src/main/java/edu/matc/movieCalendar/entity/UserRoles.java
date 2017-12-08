package edu.matc.movieCalendar.entity;

import javax.persistence.*;

/**
 * This is the hibernate entity class for the user_roles table
 *
 * @author Jamie Kruser
 */
@Entity
@Table(name = "user_roles")
public class UserRoles implements java.io.Serializable {

    @Column(name = "role_name", nullable = false, length = 15)
    private String roleName;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)
    private User user;

    /**
     * Gets the local roleName field which correlates to the user_roles table
     *
     * @return the local roleName field
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the local roleName field which correlates to the user_roles table
     *
     * @param roleName the value to set the local roleName field
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets the local user field which correlates to the user table
     *
     * @return the local user field
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the local user field which correlates to the user table
     *
     * @param user the value to set the local user field
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Checks to see if the passed in object is equal to the current UserRoles
     *
     * @param o the object to compare this UserRoles to
     * @return true if they are equal; otherwise, false
     */
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
