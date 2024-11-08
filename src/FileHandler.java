import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileHandler {
    private static final String fileName = "MovieCollection.txt";

    public static void saveMovieCollection(ArrayList<Movie> movieList) {
        try (PrintStream output = new PrintStream(fileName)) {
            for (Movie movie : movieList) {
                output.println("Title: " + movie.getTitle());
                output.println("Director: " + movie.getDirector());
                output.println("Year created: " + movie.getYearCreated());
                output.println("Is in color? : " + movie.getIsInColor());
                output.println("Length in minutes: " + movie.getLengthInMinutes());
                output.println("Genre: " + movie.getGenre());
                output.println("----------------------------"); // separator
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File could not be created: " + e.getMessage());
        }
    }

    public static ArrayList<Movie> loadMovieCollection() {
        ArrayList<Movie> movieList = new ArrayList<>();
        File file = new File(fileName);

        try (Scanner scan = new Scanner(file)) {
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

                Movie movie = new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
                movieList.add(movie);

                if (scan.hasNextLine()) {
                    scan.nextLine(); // Skip separator line
                }
            }
            System.out.println("Movie collection loaded successfully.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        } catch (InputMismatchException | NumberFormatException e) {
            throw new RuntimeException("Error reading file, format mismatch: " + e.getMessage());
        }
        return movieList;
    }
}
