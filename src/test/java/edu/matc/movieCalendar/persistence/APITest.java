package edu.matc.movieCalendar.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.themoviedb.moviesearch.MovieDataResults;
import org.themoviedb.moviesearch.ReleaseDatesItem;
import org.themoviedb.moviesearch.ReleaseDatesResults;
import org.themoviedb.moviesearch.ReleaseDatesResultsItem;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class APITest {

    @Test
    public void testMovieSearchApiJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/search/movie?api_key=25363f0be2ee0fc2fd2e9caa793b33f4&query=Jack+Reacher");

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        assertEquals("???", response);
    }

    @Test
    public void testMovieDataApi() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/movie/157336?api_key=25363f0be2ee0fc2fd2e9caa793b33f4");

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        assertEquals("???", response);
    }

    @Test
    public void testReleaseDateApiJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/movie/343611/release_dates?api_key=25363f0be2ee0fc2fd2e9caa793b33f4");

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        assertEquals("???", response);
    }


    @Test
    public void testAppendApi() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/movie/157336?api_key=25363f0be2ee0fc2fd2e9caa793b33f4&append_to_response=release_dates");

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        assertEquals("???", response);
    }

    @Test
    public void testReleaseDates() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/movie/343611/release_dates?api_key=25363f0be2ee0fc2fd2e9caa793b33f4");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        //assertEquals("???", response);

        ObjectMapper mapper = new ObjectMapper();
        ReleaseDatesResults releaseDates = mapper.readValue(response, ReleaseDatesResults.class);

        //assertEquals("???", releaseDates);

        List<ReleaseDatesResultsItem> results = new ArrayList<ReleaseDatesResultsItem>();
        results = releaseDates.getResults();

        //assertEquals("???", results);

        for (ReleaseDatesResultsItem rd : results) {
            //System.out.println(rd.toString());
            if (rd.getIso31661().equals("US")) {
                System.out.println(rd.toString());
                List<ReleaseDatesItem> item = new ArrayList<ReleaseDatesItem>();
                item = rd.getReleaseDates();

                for (ReleaseDatesItem ri : item) {
                    System.out.println(ri.toString());
                }
            }
            /**List<ReleaseDatesItem> item = new ArrayList<ReleaseDatesItem>();
            item = rd.getReleaseDates();

            for (ReleaseDatesItem ri : item) {
                System.out.println(ri.toString());
            }*/
        }
    }

    @Test
    public void testReleaseDatesUsingAppend() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/movie/343611?api_key=25363f0be2ee0fc2fd2e9caa793b33f4&append_to_response=release_dates");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        //assertEquals("???", response);

        ObjectMapper mapper = new ObjectMapper();
        MovieDataResults dataResults = mapper.readValue(response, MovieDataResults.class);

        //assertEquals("???", releaseDates);

        ReleaseDatesResults releaseDatesResults = dataResults.getReleaseDatesResults();
        List<ReleaseDatesResultsItem> results = new ArrayList<ReleaseDatesResultsItem>();
        results = releaseDatesResults.getResults();

        //assertEquals("???", results);

        for (ReleaseDatesResultsItem rd : results) {
            if (rd.getIso31661().equals("US")) {
                System.out.println(rd.toString());
                List<ReleaseDatesItem> item = new ArrayList<ReleaseDatesItem>();
                item = rd.getReleaseDates();

                for (ReleaseDatesItem ri : item) {
                    System.out.println(ri.toString());
                }
            }

            //System.out.println(rd.getIso31661());
            /**System.out.println(rd.toString());
            List<ReleaseDatesItem> item = new ArrayList<ReleaseDatesItem>();
            item = rd.getReleaseDates();

            for (ReleaseDatesItem ri : item) {
                System.out.println(ri.toString());
            } */
        }
    }

    @Test
    public void dateConverterTest() throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        System.out.println(LocalDate.parse("2016-10-21T00:00:00.000Z", dtf).toString());
    }
}
