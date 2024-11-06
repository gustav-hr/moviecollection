import Rest.Movie;
import Rest.MovieCollection;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

// Tripple A:
// Arrange - setup our test objects etc.
// Act - Do the actual calc or method run.
// Assert - check if the actual val is equal to the expected val.

class MovieCollectionTest {

    @Test
    void addMovie() {

        MovieCollection MovieCollection = new MovieCollection();
        Movie movie1 = new Movie("Superbad", "Jens Vejemann", 2005, "yes", 150, "Comedy");
        Movie movie2 = new Movie("Kickass", "Jens Vejemann", 2010, "yes", 180, "Comedy");


        MovieCollection.addMovie(movie1);
        MovieCollection.addMovie(movie2);

        int actualSize = MovieCollection.getMovieList().size();
        int expectedSize = 2;

        assertEquals(actualSize, expectedSize);
    }

    @Test
    void getMovieList() {
        // Arrange - setup our test objects etc.
        MovieCollection no1 = new MovieCollection();
        MovieCollection no2 = new MovieCollection();
        MovieCollection no3 = new MovieCollection();

        // Act - Do the actual calc or method run.
        Movie movie1 = new Movie("Titanic", "Steven Siegal", 1997, "Yes", 195, "Romantic");
        Movie movie2 = new Movie("Transformers", "Michael Bay", 2007, "Yes", 144, "Action");
        Movie movie3 = new Movie("War Dogs", "Todd Philips", 2016, "Yes", 114, "Documentary");

        no2.addMovie(movie1);
        no3.addMovie(movie2);
        no3.addMovie(movie3);

        // Assert - check if the actual val is equal to the expected val.

        int actualSize1 = no1.getMovieList().size();
        int actualSize2 = no2.getMovieList().size();
        int actualSize3 = no3.getMovieList().size();

        int expectedSize1 = 0;
        int expectedSize2 = 1;
        int expectedSize3 = 2;

        assertEquals(expectedSize1, actualSize1);
        assertEquals(expectedSize2, actualSize2);
        assertEquals(expectedSize3, actualSize3);
    }


    @Test
    void searchMovies() {
        MovieCollection number1 = new MovieCollection();

        Movie movie1 = new Movie("Titanic", "Steven Siegal", 1997, "Yes", 195, "Romantic");
        Movie movie2 = new Movie("Transformers", "Michael Bay", 2007, "Yes", 144, "Action");
        Movie movie3 = new Movie("War Dogs", "Todd Philips", 2016, "Yes", 114, "Documentary");

        number1.addMovie(movie1);
        number1.addMovie(movie2);
        number1.addMovie(movie3);

        ArrayList<Movie> searchResults = number1.searchMovies("War");

        assertEquals(1, searchResults.size(), "The search should return exactly 1 movie.");
        assertTrue(searchResults.contains(movie3), "The search result should contain 'War Dogs'.");
    }



    @Test
    public void testEditMovie() {
        MovieCollection number2 = new MovieCollection();

        Movie movie3 = new Movie("Inception", "Christopher Nolan", 2010, "yes", 148, "Sci-Fi");
        number2.addMovie(movie3);

        // Edit the movie by finding it and updating its attributes
        Movie movieToEdit = number2.editMovie("Inception");
        assertNotNull(movieToEdit, "The movie should be found for editing."); // assertNotNull Ensures that the movie to edit is found and is not null

        // Update the movie's information
        movieToEdit.setTitle("Inception 2");
        movieToEdit.setDirector("Nolan Christopher");
        movieToEdit.setYearCreated(2025);
        movieToEdit.setIsInColor("no");
        movieToEdit.setLengthInMinutes(160);
        movieToEdit.setGenre("Sci-Fi/Thriller");

        // Check if the movie's information has been updated correctly
        assertEquals("Inception 2", movieToEdit.getTitle(), "The movie title should be updated.");
        assertEquals("Nolan Christopher", movieToEdit.getDirector(), "The movie director should be updated.");
        assertEquals(2025, movieToEdit.getYearCreated(), "The movie year should be updated.");
        assertEquals("no", movieToEdit.getIsInColor(), "The movie color status should be updated.");
        assertEquals(160, movieToEdit.getLengthInMinutes(), "The movie length should be updated.");
        assertEquals("Sci-Fi/Thriller", movieToEdit.getGenre(), "The movie genre should be updated.");
    }


    @Test
    void saveMovieCollection_createsFileAndPersistsData() throws FileNotFoundException {
        // Arrange - setup movie objects
        MovieCollection movieCollection = new MovieCollection();
        Movie movie1 = new Movie("Inception", "Christopher Nolan", 2010, "yes", 148, "Sci-Fi");
        Movie movie2 = new Movie("The Matrix", "The Wachowskis", 1999, "yes", 136, "Action/Sci-Fi");

        // Act - add movies to the collection and save
        movieCollection.addMovie(movie1);
        movieCollection.addMovie(movie2);
        movieCollection.saveMovieCollection();

        File file = new File("Rest.MovieCollection.txt");

        // Assert - check that the file exists
        assertTrue(file.exists(), "The movie collection file should exist after saving.");

        // Verify content of the file
        Scanner scanner = new Scanner(file);
        String fileContent = "";
        while (scanner.hasNextLine()) {
            fileContent += scanner.nextLine() + "\n";
        }
        scanner.close();

        String expectedContent =
                "Inception\n" +
                        "Christopher Nolan\n" +
                        "2010\n" +
                        "yes\n" +
                        "148\n" +
                        "Sci-Fi\n" +
                        "----------------------------\n" +
                        "The Matrix\n" +
                        "The Wachowskis\n" +
                        "1999\n" +
                        "yes\n" +
                        "136\n" +
                        "Action/Sci-Fi\n" +
                        "----------------------------\n";

        assertEquals(expectedContent, fileContent, "The content of the file should match the saved movies.");
    }

    @Test
    void loadMovieCollection_loadsDataCorrectly() {
        // Arrange - setup movie objects and save them
        MovieCollection movieCollection = new MovieCollection();
        Movie movie1 = new Movie("Inception", "Christopher Nolan", 2010, "yes", 148, "Sci-Fi");

        movieCollection.addMovie(movie1);
        movieCollection.saveMovieCollection();

        // Act - create a new collection and load the data
        MovieCollection newCollection = new MovieCollection();
        newCollection.loadMovieCollection();

        // Assert
        assertEquals(1, newCollection.getMovieList().size(), "The movie collection should contain one movie after loading.");
        assertEquals("Inception", newCollection.getMovieList().get(0).getTitle(), "The movie title should match the saved movie.");
    }

    @Test
    void saveMovieCollection_doesNotOverwriteFileWhenNoChanges() {
        // Arrange - setup and save movie collection
        MovieCollection movieCollection = new MovieCollection();
        Movie movie1 = new Movie("Inception", "Christopher Nolan", 2010, "yes", 148, "Sci-Fi");

        movieCollection.addMovie(movie1);
        movieCollection.saveMovieCollection();

        File file = new File("Rest.MovieCollection.txt");

        // Read the content of the file after initial save
        String initialContent = "";
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                initialContent += scanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file could not be found after saving.");
            return; // Stops the test since we cannot proceed without the file
        }

        // Act - save again without making any changes
        movieCollection.saveMovieCollection();

        // Read the content of the file again after the second save
        String contentAfterNoChange = "";
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                contentAfterNoChange += scanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file could not be found during the second reading.");
            return; // Stops the test since we cannot proceed without the file
        }

        // Assert - Ensure the file content has not changed
        assertEquals(initialContent, contentAfterNoChange, "The movie collection file should not be modified if no changes were made.");
    }


}