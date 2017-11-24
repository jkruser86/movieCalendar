package edu.matc.movieCalendar.util;

import java.time.LocalDate;

/**
 * This is the class that holds all movie information
 *
 * @author Jamie Kruser
 */
public class Movie {

    private int id;
    private String image;
    private String title;
    private String description;
    private LocalDate theatricalRelease;
    private LocalDate digitalRelease;
    private LocalDate physicalRelease;

    /**
     * Generic class constructor
     */
    public Movie () {}

    /**
     * Class constructor with a passed in id
     *
     * @param id the value to set the local id variable
     */
    public Movie (int id) {
        this.id = id;
    }

    /**
     * Gets the movie id
     *
     * @return the local id variable
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the movie id
     *
     * @param id the value to set the local id variable
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the image location
     *
     * @return the local image variable
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image location
     *
     * @param image the value to set the local image variable
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the movies title
     *
     * @return the local movie variable
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the movies title
     *
     * @param title the value to set the local movie variable
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the movies description
     *
     * @return the local description variable
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the movies description
     *
     * @param description the value to set the local description variable
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the movies theatrical release date
     *
     * @return the local theatricalRelease variable
     */
    public LocalDate getTheatricalRelease() {
        return theatricalRelease;
    }

    /**
     * Sets the movies theatrical release date
     *
     * @param theatricalRelease the value to set the local theatricalRelease variable
     */
    public void setTheatricalRelease(LocalDate theatricalRelease) {
        this.theatricalRelease = theatricalRelease;
    }

    /**
     * Gets the movies digital release date
     *
     * @return the local digitalRelease variable
     */
    public LocalDate getDigitalRelease() {
        return digitalRelease;
    }

    /**
     * Sets the movies digital release date
     *
     * @param digitalRelease the value to set the local digitalRelease variable
     */
    public void setDigitalRelease(LocalDate digitalRelease) {
        this.digitalRelease = digitalRelease;
    }

    /**
     * Gets the movies physical release date
     *
     * @return the local physicalRelease variable
     */
    public LocalDate getPhysicalRelease() {
        return physicalRelease;
    }

    /**
     * Sets the movies physical release date
     *
     * @param physicalRelease the value to set the local physicalRelease variable
     */
    public void setPhysicalRelease(LocalDate physicalRelease) {
        this.physicalRelease = physicalRelease;
    }

    /**
     * Returns the string value of all local data elements
     *
     * @return all local variables strung together
     */
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
