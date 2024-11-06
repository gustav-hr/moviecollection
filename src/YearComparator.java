import java.util.Comparator;

public class YearComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        if (o1.getYearCreated() > o2.getYearCreated()) {
            return 1;
        } else if
        (o1.getYearCreated() < o2.getYearCreated()) {
            return -1;
        }
        return 0;
    }

}
