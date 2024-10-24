import java.util.Scanner;

public class UserInterface {
    public void startProgram() {
        Scanner scanner = new Scanner(System.in);
        MovieCollection movieList = new MovieCollection();
        Controller controller = new Controller(movieList);

        System.out.println("Welcome to your movie collection");
        System.out.println("To add a movie type: add");
        System.out.println("To exit the program type: exit");
        System.out.println("To see list of movies type: moviecollection");
        System.out.println("To search for a movie in your list type: search");


        String userInput = "";
        while (!userInput.equalsIgnoreCase("exit")) {
            userInput = scanner.next().toLowerCase();
            switch (userInput) {

                case "add" -> {
                    System.out.print("Title: ");
                    scanner.nextLine();
                    String title = scanner.nextLine();

                    System.out.print("Director: ");
                    String director = scanner.nextLine();

                    System.out.print("Year: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("invalid input. Please enter a valid year");
                        scanner.next();
                    }
                    int yearCreated = scanner.nextInt();

                    System.out.print("Is the movie in color: ");
                    String isInColor = scanner.next();

                    System.out.print("Length in minutes: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("invalid input. Please enter a valid time");
                        scanner.next();
                    }
                    int lengthInMinutes = scanner.nextInt();

                    System.out.print("Genre: ");
                    String genre = scanner.next();
                    controller.addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
                }
                case "exit" -> {
                    System.exit(0);
                    scanner.close();

                }
                case "moviecollection" -> {
                    System.out.println(controller.seeMoviesAdded());
                }
                case "search" -> {
                    System.out.println("Enter the title of the movie you wish to search");
                    movieList.searchMovie(scanner.next());
                }

                default -> {
                    System.out.println("Invalid input");
                }


            }
            //System.out.println("\n" + controller.seeMoviesAdded());
            System.out.println("\nTo add a movie type: add");
            System.out.println("\nTo exit the program type: exit");
            System.out.println("\nTo see list of movies type: moviecollection");
            System.out.println("To search for a movie in your list type: search");

        }
    }
}
