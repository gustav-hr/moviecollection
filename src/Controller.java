import java.util.ArrayList;

public class Controller {
private MovieCollection movieList;

    public Controller (MovieCollection movieList){
        this.movieList=movieList;
    }

    public void addMovie(String title, String director, int yearCreated, String isInColor, int lengthInMinutes, String genre){
        movieList.addMovie(title,director,yearCreated,isInColor,lengthInMinutes,genre);
    }

    public ArrayList<Movie> seeMoviesAdded(){
        return movieList.getMovieList();
    }

}
