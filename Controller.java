package Presentation;
import Business.*;
import Business.Character;

import java.util.ArrayList;

public class Controller {
    Presentation.Input input;
    Presentation.Output output;
    CharacterManager characterManager;
    private TeamManager teamManager;
    private StatsManager statsManager;
    ItemManager itemManager;

    public Controller() {
        input = new Input();
        output = new Output();
        characterManager = new CharacterManager();
        teamManager = new TeamManager();
        statsManager = new StatsManager();
        itemManager = new ItemManager();
    }

    public void executeMainOption(Presentation.OptionStartingMenu option) {
        switch (option) {
            case Presentation.OptionStartingMenu.LIST_CHARACTERS -> listCharacters();
            case Presentation.OptionStartingMenu.MANAGE_TEAMS -> manageTeams();
            case Presentation.OptionStartingMenu.LIST_ITEMS -> listItems();
            case Presentation.OptionStartingMenu.SIMULATE_COMBAT -> simulateCombat();
            case Presentation.OptionStartingMenu.EXIT -> System.out.println("We hope to see you again!");
            case Presentation.OptionStartingMenu.ELSE -> System.out.println("Option not valid!");
        }
    }

    public void executeManageTeams(Presentation.OptionManageTeam option) {
        switch (option) {
            case Presentation.OptionManageTeam.CREATE_TEAM -> listCharacters();
            case Presentation.OptionManageTeam.LIST_TEAMS -> manageTeams();
            case Presentation.OptionManageTeam.DELETE_TEAM -> listItems();
            case Presentation.OptionManageTeam.BACK -> simulateCombat();
            case OptionManageTeam.ELSE -> System.out.println("Option not valid!");
        }
    }

    private void listTeams() {
        int option;

        do {
            output.listMenu(teamManager.listTeams());
            option = input.askInteger("\nChoose an option: ");

            if (option != 0) {
                Team team = teamManager.getTeamByIndex(option);
                output.listTeamAttributes(team, characterManager.getCharactersOfTeam(team), statsManager.getStatsByIndex(option));
                input.pressAnyKeyToContinue();
            }
        } while (option != 0);
    }

    private void manageTeams() {
        int option;
        do {
            output.manageTeamsMenu();
            option = input.askInteger("\nChoose an option: ");
            //executeManageTeams(optionManageTeam.convertIntToEnum(option));
        } while (option != 4);
    }

    public void listCharacterAttributes(Character character, ArrayList<String> teamsNames){
        System.out.println(character.toString());
        System.out.println("\tTEAMS:");
        for (String name : teamsNames){
            System.out.println("\t\t" + "-" + name);
        }
    }

    private void deleteTeam() {
        System.out.println("Delete a team");
    }

    private void listCharacters() {
        int option;

        do {
            output.listMenu(characterManager.listCharacters());
            option = input.askInteger("\nChoose an option: ");

            if (option != 0) {
                output.listCharacterAttributes(characterManager.listCharacterAttribute(option), teamManager.listTeamsOfCharacter(option));
                input.pressAnyKeyToContinue();
            }
        } while (option != 0);
    }

    private void listItems() {
        int option;

        do {
            output.listMenu(itemManager.listItems());
            option = input.askInteger("\nChoose an option: ");

            if (option != 0) {
                output.listItemAttributes(itemManager.listItemAttribute(option));
                input.pressAnyKeyToContinue();
            }
        } while (option != 0);
    }

    private void simulateCombat() {
        System.out.println("Simulate combat");
    }

    public void run() {
        int option;
        do {
            output.mainMenu();
            option = input.askInteger("\nChoose an option: ");
            executeMainOption(OptionStartingMenu.convertIntToEnum(option));
        } while (option != 5);
    }

    public static void main(String[] args) {
        new Controller().run();
    }
}