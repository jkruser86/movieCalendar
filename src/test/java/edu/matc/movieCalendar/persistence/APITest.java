package edu.matc.movieCalendar.persistence;

import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;

public class APITest {

    @Test
    public void testMovieApiJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/movie/343611/release_dates?api_key=25363f0be2ee0fc2fd2e9caa793b33f4");

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        assertEquals("???", response);
    }

}
