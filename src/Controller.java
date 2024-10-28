import java.util.ArrayList;

public class Controller {
    private MovieCollection movieList;

    public Controller (MovieCollection movieList){
        this.movieList=movieList;
    }

    public void addMovie(String title, String director, int yearCreated, String isInColor, int lengthInMinutes, String genre){
        Movie movie = new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
        movieList.addMovie(movie);
    }

    public String seeMoviesAdded() {
        if (movieList.getMovieList().isEmpty()) {
            return "No movies in the collection.";
        }
        String movies = "";
        for (Movie movie : movieList.getMovieList()) {
            movies += movie.toString() + "\n";
        }
        return movies;
    }

    // Return a list of movies containing the search term in the title
    public ArrayList<Movie> seeSearchResult(String title) {
        return  movieList.searchMovies(title);
    }
    public String helpProgram(){
        return "\nTo add a movie type: add" + "\nTo exit the program type: exit";
    }

}
