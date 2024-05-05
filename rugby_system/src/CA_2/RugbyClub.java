package CA_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RugbyClub {
    // Method to read data from file and sort
    public static void sortDataFromFile(String filename) {
        List<String> names = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                names.add(line.trim());
            }
            br.close();
            Collections.sort(names); // Sorting names alphabetically
            System.out.println("Sorted List of People:");
            for (int i = 0; i < Math.min(20, names.size()); i++) {
                System.out.println(names.get(i));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

  // Method to search for a person and display coach type and team name
public static void searchData(List<Person> people, String name) {
    boolean found = false;
    for (Person person : people) {
        if (person.name.equalsIgnoreCase(name)) {
            found = true;
            System.out.println("Name: " + person.name);
            System.out.println("Coach Type: " + person.coachType);
            System.out.println("Team Name: " + person.teamName);
            break;
        }
    }
    if (!found) {
        System.out.println("Person \"" + name + "\" not found.");
    }
}

    // Method to allow user to input new data
    public static void addPlayer(Scanner scanner, List<Person> people) {
        System.out.println("Please input the Player Name:");
        String name = scanner.nextLine().trim();

        System.out.println("Please select from the following Coach Staff:");
        Arrays.stream(CoachType.values()).forEach(type -> System.out.println(type.ordinal() + 1 + ". " + type.name()));
        int coachChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Please select the Teams:");
        Arrays.stream(TeamName.values()).forEach(team -> System.out.println(team.ordinal() + 1 + ". " + team.name()));
        int teamChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validate inputs
        if (coachChoice < 1 || coachChoice > CoachType.values().length || teamChoice < 1 || teamChoice > TeamName.values().length) {
            System.out.println("Invalid input. Please try again.");
            return;
        }

        // Add new person to list
        people.add(new Person(name, CoachType.values()[coachChoice - 1], TeamName.values()[teamChoice - 1]));
        System.out.println("\"" + name + "\" has been added as \"" + CoachType.values()[coachChoice - 1] +
                "\" to \"" + TeamName.values()[teamChoice - 1] + "\" successfully!");
    }

    // Method to generate random people with coach types and teams
    public static void generateRandomPeople(List<Person> people, int count) {
    Random random = new Random();
    for (int i = 0; i < count; i++) {
        String name = "Person" + (people.size() + 1); // Incremental name
        CoachType coachType = CoachType.values()[random.nextInt(CoachType.values().length)];
        TeamName teamName = TeamName.values()[random.nextInt(TeamName.values().length)];
        Person newPerson = new Person(name, coachType, teamName);
        people.add(newPerson);
        System.out.println("\"" + newPerson.name + "\" has been added as \"" + newPerson.coachType +
                "\" to \"" + newPerson.teamName + "\" successfully!");
    }
}
}
    

