

import java.util.ArrayList;
import java.util.InputMismatchException;
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
                    //first take without using exceptions:
//                    while (!scanner.hasNextInt()) {
//                        System.out.println("Invalid input. Please enter a valid year");
//                        scanner.next();
//                    }
                    int yearCreated = 0;

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
                        }
                        catch (Exception e) {
                            System.out.println("invalid input. Enter valid year");
                        }
                    }

                    System.out.print("Genre: ");
                    scanner.nextLine();  // resets scanner from earlier input
                    String genre = scanner.nextLine();

                    controller.addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
                    System.out.println("Movie added successfully.");

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
                    scanner.close();
                    System.exit(0);
                }

                //displays menu options again
                case "help" -> System.out.println(controller.helpProgram());

                //To edit a movie
                case "edit" -> editMovie();
                case "delete" -> deleteMovie();
                default -> System.out.println("Invalid input");
            }
            System.out.println("\nTo add a movie type: add");
            System.out.println("To exit the program type: exit");
            System.out.println("If you need help, type: help.");
        }
    }


    private void editMovie() {
        String movieTitle = scanner.nextLine();
        Movie movieEdit = controller.editMovie(movieTitle);

        if (movieEdit != null) {
            System.out.println(controller.seeMoviesAdded());
            System.out.println("Type out the title of the movie you want to edit");

            scanner.nextLine();




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
                try{
                    lengthInMinutes = Integer.parseInt(scanner.next());
                    validInput=true;
                    movieEdit.setLengthInMinutes(lengthInMinutes);
                }
                catch (Exception e) {
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



