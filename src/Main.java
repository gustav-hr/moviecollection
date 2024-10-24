import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieCollection movieList = new MovieCollection();
        Controller controller = new Controller(movieList);

        System.out.println("Welcome to your movie collection");
        System.out.println("To add a movie type: add");
        System.out.println("To exit the program type: exit");


        String userInput = "";
        while (!userInput.equalsIgnoreCase("exit")) {
            userInput = scanner.next();
            switch (userInput) {

                case "add" -> {
                    System.out.print("Title: ");
                    String title = scanner.next();
                    System.out.print("Director: ");
                    String director = scanner.next();

                    System.out.print("Year: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("invalid input. Please enter a valid year");
                        scanner.next();
                    }
                    int yearCreated = scanner.nextInt();

                    System.out.print("Is the movie in color: ");
                    String isInColor = scanner.next();
                    System.out.print("Length in minutes: ");
                    int lengthInMinutes = scanner.nextInt();
                    System.out.print("Genre: ");
                    String genre = scanner.next();
                    controller.addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
                }
                case "exit" -> {
                    System.exit(0);
                    scanner.close();

                }
                default -> {
                    System.out.println("Invalid input");
                }


            }
            System.out.println("\n" + controller.seeMoviesAdded());
            System.out.println("\n" + "To add a movie type: add");
            System.out.println("To exit the program type: exit");
        }
    }
}

