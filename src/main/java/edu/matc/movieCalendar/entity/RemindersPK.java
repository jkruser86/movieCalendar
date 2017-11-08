package edu.matc.movieCalendar.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RemindersPK implements Serializable {
    private String userName;
    private int movieId;
    private String format;
    private int daysBefore;

    @Column(name = "user_name", nullable = false, length = 15)
    @Id
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "movie_id", nullable = false)
    @Id
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Column(name = "format", nullable = false, length = 15)
    @Id
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Column(name = "days_before", nullable = false)
    @Id
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

        RemindersPK that = (RemindersPK) o;

        if (movieId != that.movieId) return false;
        if (daysBefore != that.daysBefore) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (format != null ? !format.equals(that.format) : that.format != null) return false;

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
