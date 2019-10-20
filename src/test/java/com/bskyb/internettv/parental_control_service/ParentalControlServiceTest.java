package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 * ParentalControlService test class
 */
public class ParentalControlServiceTest {

    private MovieService movieService;
    private ParentalControlService parentalControlService;
    private String customerParentalControlLevel;
    private String movieId;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * called before all tests
     * instantiates common classes and mocks classes
     */
    @Before
    public void init() {
        parentalControlService = new ParentalControlServiceImpl();

        // setup mock MovieService implementation
        movieService = Mockito.mock(MovieService.class);
        parentalControlService.setMovieService(movieService);
    }

    /**
     * test permissible scenario
     * with parental control level set to "12"
     * and movie rating set to "U"
     * @throws Exception
     */
    @Test
    public void testCanWatchMovieWithLowerRating() throws Exception {
        customerParentalControlLevel = "12";
        movieId = "LOWER-U";
        when(movieService.getParentalControlLevel(movieId)).thenReturn("U");
        assertTrue(parentalControlService.canWatchMovie(customerParentalControlLevel, movieId));
    }

    /**
     * test permissible scenario
     * with parental control set to "12"
     * and movie rating set to "12"
     * @throws Exception
     */
    @Test
    public void testCanWatchMovieWithSameRating() throws Exception {
        customerParentalControlLevel = "12";
        movieId = "SAME-12";
        when(movieService.getParentalControlLevel(movieId)).thenReturn("12");
        assertTrue(parentalControlService.canWatchMovie(customerParentalControlLevel, movieId));
    }

    /**
     * test impermissible scenario
     * with parental control set to "12"
     * and movie rating set to "18"
     * @throws Exception
     */
    @Test
    public void testCanWatchMovieWithHigherRating() throws Exception {
        customerParentalControlLevel = "12";
        movieId = "HIGHER-18";
        when(movieService.getParentalControlLevel(movieId)).thenReturn("18");
        assertFalse(parentalControlService.canWatchMovie(customerParentalControlLevel, movieId));
    }

    /**
     * test impermissible scenario
     * with parental control set to "12"
     * and TitleNotFoundException induced on MovieService
     * @throws Exception
     */
    @Test
    public void testCanWatchMovieWithTitleNotFound() throws Exception {
        customerParentalControlLevel = "12";
        movieId = "NOT-FOUND-0";
        when(movieService.getParentalControlLevel(movieId)).thenThrow(TitleNotFoundException.class);
        parentalControlService.canWatchMovie(customerParentalControlLevel, movieId);
        assertTrue(parentalControlService.getMessage().equals("Title Not Found."));
    }

    /**
     * test impermissible scenario
     * with parental control set to "12"
     * and TechnicalFailure induced on MovieService
     * @throws Exception
     */
    @Test
    public void testCanWatchMovieWithTechnicalFailure() throws Exception {
        customerParentalControlLevel = "12";
        movieId = "TECH-FAIL-99";
        when(movieService.getParentalControlLevel(movieId)).thenThrow(TechnicalFailureException.class);
        parentalControlService.canWatchMovie(customerParentalControlLevel, movieId);
        assertTrue(parentalControlService.getMessage().equals("This movie cannot be watched."));
    }
}
