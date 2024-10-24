import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieCollection movieList = new MovieCollection();


        System.out.println("Welcome to your movie collection");
        System.out.println("To add a movie type: add");
        System.out.println("To exit the program type: exit");


        String userInput = "";
        while (userInput != "exit") {
            userInput = scanner.next();
            switch (userInput) {

                case "add" -> {
                    System.out.print("Title: ");
                    String title = scanner.next();
                    System.out.print("Director: ");
                    String director = scanner.next();
                    System.out.print("Year: ");
                    int yearCreated = scanner.nextInt();
                    System.out.print("Is the movie in color: ");
                    boolean isInColor = scanner.nextBoolean();
                    System.out.print("Length in minutes: ");
                    int lengthInMinutes = scanner.nextInt();
                    System.out.print("Genre: ");
                    String genre = scanner.next();
                    movieList.addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
                }
                case "exit" -> {
                    System.exit(0);
                    scanner.close();

                }
                default -> {
                    System.out.println("Invalid input");
                }


            }
            System.out.println("\n" + movieList.getMovieList());
            System.out.println("\n" + "To add a movie type: add");
            System.out.println("To exit the program type: exit");
        }
    }
}

