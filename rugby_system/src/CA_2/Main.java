package CA_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> people = new ArrayList<>();

        // Sample menu usage
        System.out.println("Please enter the filename to read:");
        String filename = scanner.nextLine().trim();
        RugbyClub.sortDataFromFile(filename);

        // Menu loop
        boolean exit = false;
        while (!exit) {
            // Display menu options
            System.out.println("Please select an option from the following:");
            for (MenuOption option : MenuOption.values()) {
                System.out.println(option.ordinal() + 1 + ". " + option.name());
            }

            // Read user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Process user choice
            switch (MenuOption.values()[choice - 1]) {
                case SORT:
                    // Sorting
                    RugbyClub.sortDataFromFile(filename);
                    break;
                case SEARCH:
                    // Searching
                    System.out.println("Please enter the name to search:");
                    String name = scanner.nextLine().trim();
                    RugbyClub.searchData(people, name);
                    break;
                case ADD_PLAYER:
                    // Adding new player
                    RugbyClub.addPlayer(scanner, people);
                    break;
                case GENERATE_RANDOM_PLAYER:
                    // Generating random people
                    RugbyClub.generateRandomPeople(people, 10); // Generate 10 random people
                    break;
                default:
                    System.out.println("Invalid option");
            }

            // Check if user wants to exit
            System.out.println("Do you want to exit? (yes/no)");
            String exitChoice = scanner.nextLine().trim();
            exit = exitChoice.equalsIgnoreCase("yes");
        }

        scanner.close();
    }
}
