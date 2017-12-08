package edu.matc.movieCalendar.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the hibernate entity class for the user table
 *
 * @author Jamie Kruser
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

    @Id
    @Column(name = "user_name", nullable = false, length = 15)
    private String userName;

    @Basic
    @Column(name = "user_pass", nullable = false, length = 15)
    private String userPass;

    @Basic
    @Column(name = "user_email", nullable = false, length = 60)
    private String userEmail;

    @OneToMany(mappedBy = "user")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private Set<UserRoles> userRoles = new HashSet<UserRoles>(0);

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private Set<Reminders> reminders = new HashSet<Reminders>(0);

    /**
     * Get the local userName field which correlates to the user_name field on the user table
     *
     * @return the local userName field
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the local userName field which correlates to the user_name field on the user table
     *
     * @param userName the value to set the local userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the local userPass field which correlates to the user_pass field on the user table
     *
     * @return the local userPass field
     */
    public String getUserPass() {
        return userPass;
    }

    /**
     * Sets the local userPass field which correlates to the user_pass field on the user table
     *
     * @param userPass the value to set the local userPass field
     */
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    /**
     * Gets the local userEmail field which correlates to the user_email field on the user table
     *
     * @return the local userEmail field
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the local userEmail field which correlates to the user_email field on the user table
     *
     * @param userEmail the value to set the local userEmail field
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Gets the local userRoles field which correlates to the user_roles table
     *
     * @return the local userRoles field
     */
    public Set<UserRoles> getUserRoles() {
        return userRoles;
    }

    /**
     * Sets the local userRoles field which correlates to the user_roles table
     *
     * @param userRoles the value to set the local userRoles field
     */
    public void setUserRoles(Set<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    /**
     * Gets the local reminders field which correlates to the reminders table
     *
     * @return the local reminders field
     */
    public Set<Reminders> getReminders() {
        return reminders;
    }

    /**
     * Sets the local reminders field which correlates to the reminders table
     *
     * @param reminders the value to set the local reminders field
     */
    public void setReminders(Set<Reminders> reminders) {
        this.reminders = reminders;
    }

    /**
     * Checks to see if the passed in object is equal to the current User
     *
     * @param o the object to compare this User to
     * @return true if they are equal; otherwise, false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (userPass != null ? !userPass.equals(user.userPass) : user.userPass != null) return false;
        if (userEmail != null ? !userEmail.equals(user.userEmail) : user.userEmail != null) return false;

        return true;
    }

    /**
     * Creates a hashcode for all the fields
     *
     * @return the hashcode for all fields
     */
    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        return result;
    }

}