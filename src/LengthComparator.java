import java.util.Comparator;

public class LengthComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        if (o1.getLengthInMinutes() > o2.getLengthInMinutes()) {
            return 1;
        } else if
        (o1.getLengthInMinutes() < o2.getLengthInMinutes()) {
            return -1;
        }
        return 0;
    }

}
