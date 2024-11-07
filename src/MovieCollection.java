import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        try (PrintStream output = new PrintStream("MovieCollection.txt")) {
            for (Movie movie : movieList) {
                output.println(movie.getTitle());
                output.println(movie.getDirector());
                output.println(movie.getYearCreated());
                output.println(movie.getIsInColor());
                output.println(movie.getLengthInMinutes());
                output.println(movie.getGenre());
                output.println("----------------------------"); // separator
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File could not be created: " + e.getMessage());
        }
    }

    public void loadMovieCollection() {
        File file = new File("MovieCollection.txt");
        Scanner scan;
        movieList.clear();
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                // Remove label parts and extract the actual values
                // The use of "replace" can be seen in the .txt. This means that we won't see the "title" before the name of the movie.
                // Easiest to see under the txt file how it works.
                //Replace method found on: https://w3schools.com/java/ref_string_replace.asp
                String title = scan.nextLine().replace("Title: ", "").trim();
                String director = scan.nextLine().replace("Director: ", "").trim();
                int yearCreated = Integer.parseInt(scan.nextLine().replace("Year created: ", "").trim());
                String isInColor = scan.nextLine().replace("Is in color? :", "").trim();
                int lengthInMinutes = Integer.parseInt(scan.nextLine().replace("Length in minutes: ", "").trim());
                String genre = scan.nextLine().replace("Genre: ", "").trim();

                // Create a new movie object and add it to the movie list
                Movie movie = new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
                movieList.add(movie);

                // Skip the separator line if it's present
                if (scan.hasNextLine()) {
                    scan.nextLine(); // Skip separator line
                }
            }
            scan.close();
            System.out.println("Movie collection loaded successfully.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        } catch (InputMismatchException | NumberFormatException e) {
            throw new RuntimeException("Error reading file, format mismatch: " + e.getMessage());
        }
    }
}



