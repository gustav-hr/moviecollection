
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private final Controller controller;
    private final MovieCollection movieList;
    private final Scanner scanner;

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
                    System.out.println("Adding movie to your movie collection... \n");

                    System.out.print("Title: ");
                    scanner.nextLine();  // handle newline from earlier input
                    String title = scanner.nextLine();

                    System.out.print("Director: ");
                    String director = scanner.nextLine();

                    System.out.print("Year: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid year");
                        scanner.next();
                    }
                    int yearCreated = scanner.nextInt();

                    System.out.print("Is the movie in color (yes/no): ");
                    String isInColor = scanner.next();

                    System.out.print("Length in minutes: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid time");
                        scanner.next();
                    }
                    int lengthInMinutes = scanner.nextInt();

                    System.out.print("Genre: ");
                    scanner.nextLine();  // handles earlier input
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
                default -> System.out.println("Invalid input");
            }
            System.out.println("\nTo add a movie type: add");
            System.out.println("To exit the program type: exit");
            System.out.println("If you need help, type: help.");
        }
    }


    private void editMovie() {
        System.out.println(controller.seeMoviesAdded());
        System.out.println("Type out the title of the movie you want to edit");

        scanner.nextLine();

        String movieTitle = scanner.nextLine();
        Movie movieEdit = movieList.editMovie(movieTitle);

        if (movieEdit != null) {
            System.out.print("Title: ");
            movieEdit.setTitle(scanner.nextLine());

            System.out.print("Director: ");
            movieEdit.setDirector(scanner.nextLine());

            System.out.print("year: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid year");
                scanner.next();
            }
            movieEdit.setYearCreated(scanner.nextInt());

            System.out.print("Is the movie in color (yes/no): ");
            movieEdit.setIsInColor(scanner.next());

            System.out.print("Length in minutes: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid time");
                scanner.next();
            }
            movieEdit.setLengthInMinutes(scanner.nextInt());

            System.out.print("Genre: ");
            scanner.nextLine();
            movieEdit.setGenre(scanner.nextLine());
        } else {
            System.out.println("Movie not found, try edit again");
        }
    }
}
