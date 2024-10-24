
import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movieList = new ArrayList<Movie>();

    public void addMovie(String title, String director, int yearCreated, String isInColor, int lengthInMinutes, String genre) {
        Movie movie = new Movie(title,director,yearCreated,isInColor,lengthInMinutes,genre);
        movieList.add(movie);
    }
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

//    @Override
//    public String toString(){
//        String allMovies = "";
//        for (Movie movie : movieList){
//            allMovies += "Title: " + movie.getTitle()
//                    + "\nDirector: " + movie.getDirector()
//                    + "\nYear created: " + movie.getYearCreated()
//                    + "\nIs in color? : " + movie.getIsInColor()
//                    + "\nLength in minutes: " + movie.getLengthInMinutes()
//                    + "\nGenre: " + movie.getGenre();
//        }
//        return allMovies;
//    }
    public void searchMovie(String searchTitle) {
        for(Movie movie : movieList) {
            if(movie.getTitle().contains(searchTitle)) {
                System.out.println(movie);
                break;
            }
        }

    }

}
