package com.bskyb.internettv.parental_control_service;

import java.util.EnumSet;
import java.util.HashMap;


/**
 * contains numerical values for Parent Control Levels
 * and a lookup to match key value pairs
 */
public enum MovieRating {

    RATING_U("U", 1),
    RATING_PG("PG", 2),
    RATING_12("12", 3),
    RATING_15("15", 4),
    RATING_18("18", 5);

    protected final String rating;
    protected final Integer value;
    private static final HashMap<String,Integer> lookup = new HashMap<String,Integer>();

    MovieRating(String rating, Integer value) {
        this.rating = rating;
        this.value = value;
    }

    static {
        for(MovieRating movieRating : EnumSet.allOf(MovieRating.class)) {
            lookup.put(movieRating.getRating(), movieRating.getValue());
        }
    }

    /**
     * returns rating level U, PG, 12, 15 or 18
     * @return String
     */
    public String getRating() {
        return rating;
    }

    /**
     * returns numerical value for rating level
     * level U = lowest
     * 18 = highest
     * @return Integer
     */
    public Integer getValue() {
        return value;
    }

    /**
     * returns rating level value for given rating
     * eg: rating: "12" returns: 3
     * @param rating
     * @return Integer
     */
    public static Integer getValue(String rating) {
        return (Integer) lookup.get(rating);
    }
}
