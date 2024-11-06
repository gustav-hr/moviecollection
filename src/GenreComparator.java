import java.util.Comparator;

public class GenreComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getGenre().compareTo(o2.getGenre());
    }
}

