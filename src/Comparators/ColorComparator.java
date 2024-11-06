package Comparators;

import Rest.Movie;

import java.util.Comparator;

public class ColorComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getIsInColor().compareTo(o2.getIsInColor());

    }
}

