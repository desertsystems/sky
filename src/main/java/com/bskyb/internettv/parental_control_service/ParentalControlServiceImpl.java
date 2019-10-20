package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;


public class ParentalControlServiceImpl implements ParentalControlService {

    private String message = "No message.";
    private MovieService movieService;


    /**
     * returns false for all restricted and true for all watchable movies
     * based on customer parental level and movie rating
     * @param customerParentalControlLevel
     * @param movieId
     * @return boolean
     */
    public boolean canWatchMovie(String customerParentalControlLevel, String movieId) {
        String movie = getMovieRating(movieId);

        return movie != null && !(MovieRating.getValue(movie) > MovieRating.getValue(customerParentalControlLevel));
    }

    /**
     * set local MovieService instance
     * @param movieService
     */
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * get movie rating level from third party
     * @param movieId
     * @return String
     */
    private String getMovieRating(String movieId) {
        try {
            return movieService.getParentalControlLevel(movieId);

        } catch(TitleNotFoundException titleNotFound) {
            this.message = "Title Not Found.";
            return null;

        } catch(TechnicalFailureException technicalFailure) {
            this.message = "This movie cannot be watched.";
            return null;
        }
    }

    /**
     * returns message to client
     * @return String
     */
    public String getMessage() {
        return this.message;
    }
}
