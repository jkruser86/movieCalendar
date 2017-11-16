package edu.matc.movieCalendar.entity;

import javax.persistence.*;

@Entity
@IdClass(RemindersPK.class)
@Table(name = "reminders")
public class Reminders {
    private String userName;
    private int movieId;
    private int theaterDaysBefore;
    private int digitalDaysBefore;
    private int physicalDaysBefore;

    @Id
    @Column(name = "user_name", nullable = false, length = 15)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Id
    @Column(name = "movie_id", nullable = false)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Basic
    @Column(name = "theater_days_before", nullable = false)
    public int getTheaterDaysBefore() {
        return theaterDaysBefore;
    }

    public void setTheaterDaysBefore(int theaterDaysBefore) {
        this.theaterDaysBefore = theaterDaysBefore;
    }

    @Basic
    @Column(name = "digital_days_before", nullable = false)
    public int getDigitalDaysBefore() {
        return digitalDaysBefore;
    }

    public void setDigitalDaysBefore(int digitalDaysBefore) {
        this.digitalDaysBefore = digitalDaysBefore;
    }

    @Basic
    @Column(name = "physical_days_before", nullable = false)
    public int getPhysicalDaysBefore() {
        return physicalDaysBefore;
    }

    public void setPhysicalDaysBefore(int physicalDaysBefore) {
        this.physicalDaysBefore = physicalDaysBefore;
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
        if (userName != null ? !userName.equals(reminders.userName) : reminders.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + movieId;
        result = 31 * result + theaterDaysBefore;
        result = 31 * result + digitalDaysBefore;
        result = 31 * result + physicalDaysBefore;
        return result;
    }
}
