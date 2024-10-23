import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieCollection movieList = new MovieCollection();

        String title = scanner.nextLine();
        String director = scanner.nextLine();
        int yearCreated = scanner.nextInt();
        boolean isInColor = scanner.nextBoolean();
        int lengthInMinutes = scanner.nextInt();
        String genre = scanner.next();
        System.out.println(movieList);
        movieList.addMovie(title,director,yearCreated,isInColor,lengthInMinutes,genre);


        System.out.println(movieList);
    }
}

