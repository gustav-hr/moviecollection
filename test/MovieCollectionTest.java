import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        //TODO gustav skal lige fikse så der ikke står assertnotnull
        assertNotNull(movieToEdit, "The movie should be found for editing.");

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

}