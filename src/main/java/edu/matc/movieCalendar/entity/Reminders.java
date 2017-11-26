package edu.matc.movieCalendar.entity;

import javax.persistence.*;

/**
 * This is the hibernate entity class for the reminders table
 *
 * @author Jamie Kruser
 */
@Entity
@Table(name = "reminders")
public class Reminders implements java.io.Serializable {

    @Id
    @Column(name = "movie_id", nullable = false)
    private int movieId;

    @Column(name = "theater_days_before", nullable = false)
    private int theaterDaysBefore;

    @Column(name = "digital_days_before", nullable = false)
    private int digitalDaysBefore;

    @Column(name = "physical_days_before", nullable = false)
    private int physicalDaysBefore;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_name", referencedColumnName = "user_name", nullable = false)
    private User user;

    /**
     * Gets the movie_id field from the reminders table
     *
     * @return the movie_id
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Sets the local movieId variable
     *
     * @param movieId the value to set the local movieId variable
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    /**
     * Gets the theater_days_before field from the reminders table
     * @return
     */
    public int getTheaterDaysBefore() {
        return theaterDaysBefore;
    }

    public void setTheaterDaysBefore(int theaterDaysBefore) {
        this.theaterDaysBefore = theaterDaysBefore;
    }

    public int getDigitalDaysBefore() {
        return digitalDaysBefore;
    }

    public void setDigitalDaysBefore(int digitalDaysBefore) {
        this.digitalDaysBefore = digitalDaysBefore;
    }

    public int getPhysicalDaysBefore() {
        return physicalDaysBefore;
    }

    public void setPhysicalDaysBefore(int physicalDaysBefore) {
        this.physicalDaysBefore = physicalDaysBefore;
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

        Reminders reminders = (Reminders) o;

        if (movieId != reminders.movieId) return false;
        if (theaterDaysBefore != reminders.theaterDaysBefore) return false;
        if (digitalDaysBefore != reminders.digitalDaysBefore) return false;
        if (physicalDaysBefore != reminders.physicalDaysBefore) return false;
        if (user != reminders.user) return false;
        //if (userName != null ? !userName.equals(reminders.userName) : reminders.userName != null) return false;

        return true;
    }
}
