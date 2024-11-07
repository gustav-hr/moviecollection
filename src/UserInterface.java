import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class UserInterface {

    private final Controller controller;
    private final MovieCollection movieList;
    private final Scanner scanner;
    private boolean validInput = false;

    public UserInterface() {
        this.movieList = new MovieCollection();
        this.controller = new Controller(movieList);
        this.scanner = new Scanner(System.in);
    }

    // Start of program and first prompt to user.
    public void startProgram() {
        System.out.println("Welcome to your movie collection.");
        System.out.println("To add a movie type: add.");
        System.out.println("To see your list of movies in its current state type: list.");
        System.out.println("To search for a movie in your list type: search.");
        System.out.println("To edit a movie from your list type: edit.");
        System.out.println("If you need help, type: help.");
        System.out.println("To exit the program type: exit.");


        String userInput = "";
        while (!userInput.equalsIgnoreCase("exit")) {
            userInput = scanner.next().toLowerCase();
            switch (userInput) {

                //Add a movie
                case "add" -> {
                    System.out.println("Adding movie to your movie collection... \n");

                    System.out.print("Title: ");
                    scanner.nextLine();  //  resets scanner from earlier input
                    String title = scanner.nextLine();

                    System.out.print("Director: ");
                    String director = scanner.nextLine();

                    System.out.print("Year: ");
                    int yearCreated = 0;
                    validInput = false;
                    while (!validInput) {
                        try {
                            yearCreated = Integer.parseInt(scanner.next());
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Enter valid Year:");
                        }
                    }

                    System.out.print("Is the movie in color (yes/no): ");
                    String isInColor = scanner.next();

                    System.out.print("Length in minutes: ");
                    int lengthInMinutes = 0;
                    validInput = false;
                    while (!validInput) {
                        try {
                            lengthInMinutes = Integer.parseInt(scanner.next());
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("invalid input. Enter valid year");
                        }
                    }

                    System.out.print("Genre: ");
                    scanner.nextLine();  // resets scanner from earlier input
                    String genre = scanner.nextLine();

                    controller.addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
                    System.out.println("Movie added successfully.");

                }

                // How to sort the movies in two different variables. ( User stories 13-15 ).

                case "sort" -> {
                    if (!controller.seeMovieList().isEmpty()) {
                        System.out.println("How do you want to sort your movies?" +
                                "\n Please choose up to 2 variables in the order you want the movie collection to be sorted." +
                                "\n Your options are: Title, year, length, genre, color, director.\nIf you want to sort with only one variable. Please type the variable twice with ENTER in between");

                        Comparator comparator1 = null;

                        String comparatorInput1 = scanner.next();

                        switch (comparatorInput1.toLowerCase()) {
                            case "title" -> {
                                comparator1 = new TitleComparator();
                            }
                            case "year" -> {
                                comparator1 = new YearComparator();
                            }
                            case "length" -> {
                                comparator1 = new LengthComparator();
                            }
                            case "color" -> {
                                comparator1 = new ColorComparator();
                            }
                            case "genre" -> {
                                comparator1 = new GenreComparator();
                            }
                            case "director" -> {
                                comparator1 = new DirectorComparator();
                            }
                        }
                        // The second variable.

                        Comparator comparator2 = null;

                        String comparatorInput2 = scanner.next();

                        switch (comparatorInput2.toLowerCase()) {
                            case "title" -> {
                                comparator2 = new TitleComparator();
                            }
                            case "year" -> {
                                comparator2 = new YearComparator();
                            }
                            case "length" -> {
                                comparator2 = new LengthComparator();
                            }
                            case "color" -> {
                                comparator2 = new ColorComparator();
                            }
                            case "genre" -> {
                                comparator2 = new GenreComparator();
                            }
                            case "director" -> {
                                comparator2 = new DirectorComparator();
                            }
                        }

                        Collections.sort(movieList.getMovieList(), comparator1.thenComparing(comparator2));
                        System.out.println("Here is the sorted list: \n" + controller.seeMoviesAdded());

                    } else {
                        System.out.println("No movies to sort!");
                    }
                }


                //To see the movie list as it is
                case "list" -> System.out.println(controller.seeMoviesAdded());

                //to search for a movie
                case "search" -> {
                    System.out.println("Enter the title of the movie you wish to search for: ");
                    scanner.nextLine();  // Handles new line from earlier input
                    String title = scanner.nextLine();
                    ArrayList<Movie> searchResults = controller.seeSearchResult(title);

                    if (!searchResults.isEmpty()) {
                        System.out.println("Movies found:");
                        for (Movie movie : searchResults) {
                            System.out.println(movie);
                        }
                    } else {
                        System.out.println("No movies found matching your search.");
                    }
                }

                //To exit the program
                case "exit" -> {
                    System.out.println("Exiting the program...");
                    movieList.saveMovieCollection();
                    scanner.close();
                    System.exit(0);
                }

                //displays menu options again
                case "help" -> {
                    System.out.println(controller.helpProgram());
                }

                case "edit" -> editMovie(); // The code for editing a movie.
                case "delete" -> deleteMovie(); // the code for deleting a movie.
                case "save" -> {
                    if (!controller.seeMovieList().isEmpty()) {
                        controller.saveMovielistToFile(); // The code for saving a movie.
                        System.out.println("Movie collection saved successfully");
                    } else {
                        System.out.println("No movies to save...");
                    }
                }
                case "load" ->
                        controller.loadMovieListFromFile(); // The code for loading a movie from the moviecollection.txt file.
                default ->
                        System.out.println("Invalid input"); // The default setting is invalid input. If the program can not recognize whatever input comes from the user, this will be the output.


            }
            // This will be printed almost no matter what code you enter.
            System.out.println("\nTo add a movie type: add.");
            System.out.println("To exit the program type: exit.");
            System.out.println("If you need help, type: help.");

        }
    }


    private void editMovie() {
        scanner.nextLine();
        System.out.println(controller.seeMoviesAdded());
        System.out.println("Type out the title of the movie you want to edit ");

        String movieTitle = scanner.nextLine();
        Movie movieEdit = controller.editMovie(movieTitle);

        if (movieEdit != null) {


            System.out.print("Title: ");
            movieEdit.setTitle(scanner.nextLine());

            System.out.print("Director: ");
            movieEdit.setDirector(scanner.nextLine());

            System.out.print("Year: ");
            int yearCreated = 0;
            validInput = false;
            while (!validInput) {
                try {
                    yearCreated = Integer.parseInt(scanner.next());
                    validInput = true;
                    movieEdit.setYearCreated(yearCreated);
                } catch (Exception e) {
                    System.out.println("Invalid input. Enter valid Year:");
                }
            }
            scanner.nextLine();

            System.out.print("Is the movie in color (yes/no): ");
            movieEdit.setIsInColor(scanner.next());

            System.out.print("Length in minutes: ");
            int lengthInMinutes;
            validInput = false;
            while (!validInput) {
                try {
                    lengthInMinutes = Integer.parseInt(scanner.next());
                    validInput = true;
                    movieEdit.setLengthInMinutes(lengthInMinutes);
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid time");
                }
            }

            System.out.print("Genre: ");
            scanner.nextLine();
            movieEdit.setGenre(scanner.nextLine());
            System.out.println("Movie updated successfully");
        } else {
            System.out.println("No movie found, either the movie doesn't exist or your movie collection is empty.");
        }
    }

    private void deleteMovie() {
        System.out.println("Enter the title of the movie you wish to delete: ");
        scanner.nextLine();  // Håndter newline fra tidligere input
        String title = scanner.nextLine();

        if (controller.deleteMovie(title)) {
            System.out.println("Movie: '" + title + "' deleted successfully.");
        } else {
            System.out.println("Movie not found, deletion failed.");
        }
    }

}











