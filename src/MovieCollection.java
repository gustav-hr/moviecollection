import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movieList = new ArrayList<Movie>();

    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        Movie movie = new Movie(title,director,yearCreated,isInColor,lengthInMinutes,genre);
        movieList.add(movie);
    }


    @Override
    public String toString(){
        for (Movie movie : movieList){
            return "Title: " + movie.getTitle()+ "\nDirector: " + movie.getDirector()
                    + "\nYear created: " + movie.getYearCreated() + "\nIs in color? : "
                    + movie.getIsInColor() + "\nLength in minutes: " + movie.getLengthInMinutes() + "\nGenre: " + movie.getGenre();
        }
        return "";
    }
}
