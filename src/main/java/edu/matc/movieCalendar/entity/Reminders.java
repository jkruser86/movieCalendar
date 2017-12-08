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
     * Gets the local movieId field which correlates to the movie_id field on the reminders table
     *
     * @return the local movieId field
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Sets the local movieId variable which correlates to the movie_id field on the reminders table
     *
     * @param movieId the value to set the local movieId variable
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    /**
     * Gets the local theaterDaysBefore field which correlates to the theater_days_before on the reminders table
     *
     * @return the local theaterDaysBefore field
     */
    public int getTheaterDaysBefore() {
        return theaterDaysBefore;
    }

    /**
     * Sets the local theaterDaysBefore field which correlates to the theater_days_before field on the reminders table
     *
     * @param theaterDaysBefore the value to set the local theaterDaysBefore field
     */
    public void setTheaterDaysBefore(int theaterDaysBefore) {
        this.theaterDaysBefore = theaterDaysBefore;
    }

    /**
     * Gets the local digitalDaysBefore field which correlates to the digital_days_before field on the reminders table
     *
     * @return the local digitalDaysBefore field
     */
    public int getDigitalDaysBefore() {
        return digitalDaysBefore;
    }

    /**
     * Sets the local digitalDaysBefore field which correlates to the digital_days_before field on the reminders table
     *
     * @param digitalDaysBefore the value to set the local digitalDaysBefore field
     */
    public void setDigitalDaysBefore(int digitalDaysBefore) {
        this.digitalDaysBefore = digitalDaysBefore;
    }

    /**
     * Gets the local physicalDaysBefore field which correlates to the physical_days_before field on the reminders table
     *
     * @return the local physicalDaysBefore field
     */
    public int getPhysicalDaysBefore() {
        return physicalDaysBefore;
    }

    /**
     * Sets the local physicalDaysBefore field which correlates to the physical_days_before field on the reminders table
     *
     * @param physicalDaysBefore the value to set the local physicalDaysBefore field
     */
    public void setPhysicalDaysBefore(int physicalDaysBefore) {
        this.physicalDaysBefore = physicalDaysBefore;
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
     * Checks to see if the passed in object is equal to the current Reminder
     *
     * @param o the object to compare this Reminder to
     * @return true if they are equal; otherwise, false
     */
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

        return true;
    }
}
