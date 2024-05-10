package CA_2;

import java.io.*;
import java.util.*;


public class RugbyClub {
    // Method to read data from file

    public static List<Person> readDataFromFile(String filename) {
        List<Person> people = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }
                String[] parts = line.split(",");
                String name = parts[1].trim() + " " + parts[2].trim();
                // Assuming the email is the 4th column and gender is the 5th column
                String email = parts[3].trim();
                String gender = parts[4].trim();
                // For simplicity, let's assume CoachType and TeamName are always HEAD_COACH and A_SQUAD
                people.add(new Person(name, CoachType.HEAD_COACH, TeamName.A_SQUAD));
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return people;
    }

    // Merge Sort Algorithm
    public static void mergeSort(List<Person> arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(List<Person> arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        List<Person> L = new ArrayList<>(arr.subList(left, mid + 1));
        List<Person> R = new ArrayList<>(arr.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L.get(i).name.compareTo(R.get(j).name) <= 0) {
                arr.set(k++, L.get(i++));
            } else {
                arr.set(k++, R.get(j++));
            }
        }

        while (i < n1) {
            arr.set(k++, L.get(i++));
        }

        while (j < n2) {
            arr.set(k++, R.get(j++));
        }
    }

    // Method to search for a person by name
    public static void searchByName(List<Person> people, String name) {
        for (Person person : people) {
            if (person.name.equalsIgnoreCase(name)) {
                System.out.println("Name: " + person.name);
                System.out.println("Coach Type: " + person.coachType);
                System.out.println("Team Name: " + person.teamName);
                return;
            }
        }
        System.out.println("Person \"" + name + "\" not found.");
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
        System.out.println("\"" + name + "\" has been added as \"" + CoachType.values()[coachChoice - 1]
                + "\" to \"" + TeamName.values()[teamChoice - 1] + "\" successfully!");
    }

    // Method to generate random people with coach types and teams
public static void generateRandomPeople(List<Person> people, int count) {
    Random random = new Random();
    String[] firstNames = {"John", "Jane", "Michael", "Emily", "David", "Emma", "Chris", "Jessica", "Daniel", "Sarah"};
    String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};

    Set<String> generatedNames = new HashSet<>();

    while (count > 0) {
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String fullName = firstName + " " + lastName;

        CoachType coachType = CoachType.values()[random.nextInt(CoachType.values().length)];
        TeamName teamName = TeamName.values()[random.nextInt(TeamName.values().length)];

        // Check if the generated combination of name, coach type, and team name is unique
        String combination = fullName + "_" + coachType + "_" + teamName;
        if (!generatedNames.contains(combination)) {
            Person newPerson = new Person(fullName, coachType, teamName);
            people.add(newPerson);
            generatedNames.add(combination);

            System.out.println("\"" + newPerson.name + "\" has been added as \"" + newPerson.coachType +
                    "\" to \"" + newPerson.teamName + "\" successfully!");
            count--;
        }
    }
}


    // Method to sort and display dummy data from file
    public static void sortAndDisplayDummyData(String filename) {
        List<Person> people = readDataFromFile(filename);
        if (people.isEmpty()) {
            System.out.println("No data found in the file.");
            return;
        }

        mergeSort(people, 0, people.size() - 1);
        System.out.println("Sorted List of People:");
        int count = Math.min(20, people.size());
        for (int i = 0; i < count; i++) {
            Person person = people.get(i);
            System.out.println("Name: " + person.name);
            System.out.println("Coach Type: " + person.coachType);
            System.out.println("Team Name: " + person.teamName);
            System.out.println();
        }
    }
}
