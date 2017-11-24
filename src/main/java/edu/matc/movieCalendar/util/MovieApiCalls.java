package edu.matc.movieCalendar.util;

import com.fasterxml.jackson.databind.ObjectMapper;
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

/**
 * Does all the calls to the themoviedb api
 *
 * @author Jamie Kruser
 */
public class MovieApiCalls {

    private final String apiKey = "25363f0be2ee0fc2fd2e9caa793b33f4";
    private final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * Generic class constructor
     */
    public MovieApiCalls() {}

    /**
     * Calls the api using a search term and returns all movie results
     *
     * @param term the term to search for
     * @return all results from the api call for the searched term
     * @throws IOException handles the IOException from the api call
     */
    public List<MovieResultsItem> getAllResults(String term) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + term);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        MovieResults results = mapper.readValue(response, MovieResults.class);

        return results.getResults();
    }

    /**
     * Calls the api using the movie id and returns the movie info
     *
     * @param id the movie id to use for the api call
     * @return the movie info
     * @throws IOException handles the IOException from the api call
     */
    public Movie getMovieInfo(int id) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey + "&append_to_response=release_dates");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        MovieDataResults dataResults = mapper.readValue(response, MovieDataResults.class);

        ReleaseDatesResults releaseDatesResults = dataResults.getReleaseDatesResults();
        List<ReleaseDatesResultsItem> results = new ArrayList<ReleaseDatesResultsItem>();
        results = releaseDatesResults.getResults();

        Movie movie = new Movie(id);
        movie.setImage(dataResults.getPosterPath());
        movie.setTitle(dataResults.getTitle());
        movie.setDescription(dataResults.getOverview());

        for (ReleaseDatesResultsItem rd : results) {
            if (rd.getIso31661().equals("US")) {
                List<ReleaseDatesItem> item = new ArrayList<ReleaseDatesItem>();
                item = rd.getReleaseDates();

                for (ReleaseDatesItem ri : item) {
                    if (ri.getType() == 3) {
                        movie.setTheatricalRelease(LocalDate.parse(ri.getReleaseDate(), DateTimeFormatter.ofPattern(dateTimeFormat)));
                    }

                    if (ri.getType() == 4) {
                        movie.setDigitalRelease(LocalDate.parse(ri.getReleaseDate(), DateTimeFormatter.ofPattern(dateTimeFormat)));
                    }

                    if (ri.getType() == 5) {
                        movie.setPhysicalRelease(LocalDate.parse(ri.getReleaseDate(), DateTimeFormatter.ofPattern(dateTimeFormat)));
                    }
                }
            }
        }

        return movie;
    }
}
