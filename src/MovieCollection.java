

import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movieList = new ArrayList<Movie>();

    public void addMovie(String title, String director, int yearCreated, String isInColor, int lengthInMinutes, String genre) {
        Movie movie = new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
        movieList.add(movie);
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public Movie searchMovie(String searchTitle) {
        for (Movie movie : movieList) {
            if (movie.getTitle().contains(searchTitle)) {
                return movie;

            }
        }
        return null;
    }

    public Movie editMovie(String movieTitle) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(movieTitle)) {
                return movie;

            }
        }
        return null;
    }

}
