package edu.matc.movieCalendar.util;

import org.junit.Before;
import org.junit.Test;
import org.themoviedb.moviesearch.MovieResults;
import org.themoviedb.moviesearch.MovieResultsItem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MovieApiCallsTest {

    private MovieApiCalls movieApiCalls;
    private List<MovieResultsItem> movieResultsItems;
    private Movie movie;

    @Before
    public void setUp() throws Exception {
        movieApiCalls = new MovieApiCalls();
        movieResultsItems = new ArrayList<MovieResultsItem>();
        movie = new Movie();
    }

    @Test
    public void getAllResultsTest() throws Exception {
        movieResultsItems = movieApiCalls.getAllResults("The+Last+Jedi");

        assertTrue(movieResultsItems.size() > 0);
    }

    @Test
    public void getMovieInfoTest() throws Exception {
        movie = movieApiCalls.getMovieInfo(181808);

        assertEquals(movie.getTitle(), "Star Wars: The Last Jedi");
    }

}