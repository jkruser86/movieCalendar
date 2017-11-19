package edu.matc.movieCalendar.util;

import java.time.LocalDate;

public class Movie {

    private int id;
    private String image;
    private String title;
    private String description;
    private LocalDate theatricalRelease;
    private LocalDate digitalRelease;
    private LocalDate physicalRelease;

    public Movie () {}

    public Movie (int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTheatricalRelease() {
        return theatricalRelease;
    }

    public void setTheatricalRelease(LocalDate theatricalRelease) {
        this.theatricalRelease = theatricalRelease;
    }

    public LocalDate getDigitalRelease() {
        return digitalRelease;
    }

    public void setDigitalRelease(LocalDate digitalRelease) {
        this.digitalRelease = digitalRelease;
    }

    public LocalDate getPhysicalRelease() {
        return physicalRelease;
    }

    public void setPhysicalRelease(LocalDate physicalRelease) {
        this.physicalRelease = physicalRelease;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", theatricalRelease=" + theatricalRelease +
                ", digitalRelease=" + digitalRelease +
                ", physicalRelease=" + physicalRelease +
                '}';
    }
}
