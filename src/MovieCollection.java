import java.util.ArrayList;


public class MovieCollection {
    private ArrayList<Movie> movieList = new ArrayList<Movie>();

    public void addMovie(Movie movie) {
        movieList.add(movie);
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<Movie> searchMovies(String title) {
        ArrayList<Movie> matchingMovies = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingMovies.add(movie);
            }
        }
        return matchingMovies;
    }

    public Movie editMovie(String movieTitle) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(movieTitle)) {
                return movie;

            }
        }
        return null;
    }

    public boolean deleteMovie(String title) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                movieList.remove(movie);
                return true; // Filmen blev fundet og slettet
            }
        }
        return false; // Ingen film blev fundet med den angivne titel
    }

    public void saveMovieCollection() {
        FileHandler.saveMovieCollection(movieList);
    }

    public void loadMovieCollection() {
        movieList = FileHandler.loadMovieCollection();
    }
}



