import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        String director = scanner.nextLine();
        int yearCreated = scanner.nextInt();
        boolean isInColor = scanner.nextBoolean();
        int lengthInMinutes = scanner.nextInt();
        String genre = scanner.nextLine();

        Movie movie = new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre);

        System.out.println(movie);
    }
}

