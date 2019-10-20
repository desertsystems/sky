package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.MovieService;


public interface ParentalControlService {

    /**
     * returns restricted or watchable
     * based on customer parental level and movie rating
     * @param customerParentalControlLevel
     * @param movieId
     * @return boolean
     */
    boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws Exception;

    /**
     * set local MovieService instance
     * @param movieService
     */
    void setMovieService(MovieService movieService);

    /**
     * returns message to client
     * @return String
     */
    String getMessage();
}
