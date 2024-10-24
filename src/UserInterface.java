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

    // Start programmet og håndter input fra brugeren
    public void startProgram() {
        System.out.println("Welcome to your movie collection.");
        System.out.println("To add a movie type: add.");
        System.out.println("To exit the program type: exit.");
        System.out.println("To see list of movies type: movie collection.");
        System.out.println("To search for a movie in your list type: search.");
        System.out.println("If you need help, type: help.");

        String userInput = "";
        while (!userInput.equalsIgnoreCase("exit")) {
            userInput = scanner.next().toLowerCase();
            switch (userInput) {
                case "add" -> addMovie();
                case "list" -> printMovieCollection();
                case "search" -> searchMovie();
                case "exit" -> exitProgram();
                case "help" -> helpProgram();
                case "edit"-> editMovie();
                default -> System.out.println("Invalid input");
            }
            System.out.println("\nTo add a movie type: add");
            System.out.println("To exit the program type: exit");
        }
    }
    private void helpProgram() {
        System.out.println("\nTo see list of movies type: list");
        System.out.println("To search for a movie in your list type: search");
    }

    // Metode til at tilføje en film
    private void addMovie() {
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

    // Method to print all movies
    private void printMovieCollection() {
        System.out.println(controller.seeMoviesAdded());
    }

    // Search Method
    private void searchMovie() {
        System.out.println("Enter the title of the movie you wish to search for:");
        scanner.nextLine();  // For at håndtere newline fra tidligere input
        String title = scanner.nextLine();

        if(title != null) {
            movieList.searchMovie(title);
        } else {
            System.out.println("Movie not found, try searching again");
        }
    }

    // Method for exiting program
    private void exitProgram() {
        System.out.println("Exiting the program...");
        scanner.close();
        System.exit(0);
    }

    private void editMovie() {
        System.out.println(movieList.getMovieList());
        System.out.println("Type out the title of the movie you want to edit");
        Movie movieEdit = movieList.EditMovie(scanner.next());
        if(movieEdit != null) {
            System.out.print("Title: ");
            movieEdit.setTitle(scanner.next());
            System.out.println("Director: ");
            movieEdit.setDirector(scanner.next());
            System.out.println("year: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid year");
                scanner.next();
            }
            movieEdit.setYearCreated(scanner.nextInt());
            System.out.println("Is the movie in color (yes/no): ");
            movieEdit.setIsInColor(scanner.next());
            System.out.println("Length in minutes: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid time");
                scanner.next();
            }
            movieEdit.setLengthInMinutes(scanner.nextInt());
            System.out.println("Genre: ");
            movieEdit.setGenre(scanner.next());
        }
        else {
            System.out.println("Movie not found, try edit again");
        }

    }
    }

