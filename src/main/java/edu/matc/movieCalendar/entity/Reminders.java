package edu.matc.movieCalendar.entity;

import javax.persistence.*;

@Entity
@IdClass(RemindersPK.class)
@Table(name = "reminders")
public class Reminders {
    private String userName;
    private int movieId;
    private String format;
    private int daysBefore;

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

    @Id
    @Column(name = "format", nullable = false, length = 15)
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Id
    @Column(name = "days_before", nullable = false)
    public int getDaysBefore() {
        return daysBefore;
    }

    public void setDaysBefore(int daysBefore) {
        this.daysBefore = daysBefore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reminders reminders = (Reminders) o;

        if (movieId != reminders.movieId) return false;
        if (daysBefore != reminders.daysBefore) return false;
        if (userName != null ? !userName.equals(reminders.userName) : reminders.userName != null) return false;
        if (format != null ? !format.equals(reminders.format) : reminders.format != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + movieId;
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + daysBefore;
        return result;
    }
}
