package edu.matc.movieCalendar.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//TODO: Need to add javadoc
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


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Set<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Reminders> getReminders() {
        return reminders;
    }

    public void setReminders(Set<Reminders> reminders) {
        this.reminders = reminders;
    }

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

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        return result;
    }

}