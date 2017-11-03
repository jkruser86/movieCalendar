package edu.matc.movieCalendar.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.themoviedb.moviesearch.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Movie {

    private final Logger log = Logger.getLogger(this.getClass());
    private final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private int id;
    private String image;
    private String title;
    private String description;
    private LocalDate theatricalRelease;
    private LocalDate digitalRelease;
    private LocalDate physicalRelease;

    public Movie (MovieResultsItem results, String apiKey) {
        this.id = results.getId();
        this.image = results.getPosterPath();
        this.title = results.getTitle();
        this.description = results.getOverview();
        try {
            setReleaseDates(apiKey);
        } catch (IOException io) {
            log.error("Error getting release date for " + this.title, io);
        }

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

    public void setReleaseDates(String apiKey) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey + "&append_to_response=release_dates");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        MovieDataResults dataResults = mapper.readValue(response, MovieDataResults.class);

        ReleaseDatesResults releaseDatesResults = dataResults.getReleaseDatesResults();
        List<ReleaseDatesResultsItem> results = new ArrayList<ReleaseDatesResultsItem>();
        results = releaseDatesResults.getResults();

        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateTimeFormat);

        for (ReleaseDatesResultsItem rd : results) {
            if (rd.getIso31661().equals("US")) {
                List<ReleaseDatesItem> item = new ArrayList<ReleaseDatesItem>();
                item = rd.getReleaseDates();

                for (ReleaseDatesItem ri : item) {
                    if (ri.getType() == 3) {
                        theatricalRelease = LocalDate.parse(ri.getReleaseDate(), DateTimeFormatter.ofPattern(dateTimeFormat));
                    }

                    if (ri.getType() == 4) {
                        digitalRelease = LocalDate.parse(ri.getReleaseDate(), DateTimeFormatter.ofPattern(dateTimeFormat));
                    }

                    if (ri.getType() == 5) {
                        physicalRelease = LocalDate.parse(ri.getReleaseDate(), DateTimeFormatter.ofPattern(dateTimeFormat));
                    }
                }
            }
        }


    }

}
