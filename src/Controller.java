

import java.util.ArrayList;

public class Controller {
    private MovieCollection movieList;

    public Controller (MovieCollection movieList){
        this.movieList=movieList;
    }

    public void addMovie(String title, String director, int yearCreated, String isInColor, int lengthInMinutes, String genre){
        movieList.addMovie(title,director,yearCreated,isInColor,lengthInMinutes,genre);
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

    public Movie seeSearchResult(String title){
        for(Movie movie : movieList.getMovieList()) {

            return movieList.searchMovie(title);
        }
        return null;
    }
    public String helpProgram(){
        return "\nTo add a movie type: add" + "\nTo exit the program type: exit";
    }
}
