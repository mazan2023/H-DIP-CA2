package CA_2;

enum MenuOption {
    SORT, SEARCH, ADD_PLAYER, GENERATE_RANDOM_PLAYER;
}

// Enum for Coach types
enum CoachType {
    HEAD_COACH, ASSISTANT_COACH, SCRUM_COACH;
}

// Enum for Team names
enum TeamName {
    A_SQUAD, B_SQUAD, UNDER_13_SQUAD;
}

// Person class to represent individuals
class Person {

    String name;
    CoachType coachType;
    TeamName teamName;

    public Person(String name, CoachType coachType, TeamName teamName) {
        this.name = name;
        this.coachType = coachType;
        this.teamName = teamName;
    }
}
