package Presentation;
import Business.*;
import Business.Character;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private final Input input;
    private final Output output;
    private final CharacterManager characterManager;
    private final TeamManager teamManager;
    private final StatsManager statsManager;
    private final ItemManager itemManager;
    private final MemberManager memberManager;

    static final String INVALID_OPTION_MESSAGE = "Option not valid!";
    static final String CHOOSE_OPTION_MESSAGE = "\nChoose and option: ";
    static final String TEAM_NO_EXIST_MESSAGE = "Team doesn't exist";
    static final String INITIALIZING_TEAMS_MESSAGE = "Initializing teams...";
    static final String STARTING_SIMULATION_MESSAGE = "\nStarting simulation...";
    static final String STRATEGY_BALANCED_MESSAGE = "\t1) Balanced";
    static final String LOOKING_AVAILABLE_TEAMS = "Looking for available teams...";

    public Controller() {
        input = new Input();
        output = new Output();
        characterManager = new CharacterManager();
        teamManager = new TeamManager();
        statsManager = new StatsManager();
        itemManager = new ItemManager();
        memberManager = new MemberManager();
    }

    public void executeMainOption(OptionStartingMenu option) throws IOException {
        switch (option) {
            case OptionStartingMenu.LIST_CHARACTERS -> listCharacters();
            case OptionStartingMenu.MANAGE_TEAMS -> manageTeams();
            case OptionStartingMenu.LIST_ITEMS -> listItems();
            case OptionStartingMenu.SIMULATE_COMBAT -> simulateCombat();
            case OptionStartingMenu.EXIT -> Output.printPhrase("We hope to see you again!");
            case OptionStartingMenu.ELSE -> Output.printPhrase("Option not valid!");
        }
    }

    public void executeManageTeams(OptionManageTeam option) throws IOException {
        switch (option) {
            case OptionManageTeam.CREATE_TEAM -> createTeam();
            case OptionManageTeam.LIST_TEAMS -> listTeams();
            case OptionManageTeam.DELETE_TEAM -> deleteTeam();
            case OptionManageTeam.BACK -> {
                break;
            }
            case OptionManageTeam.ELSE -> System.out.println(INVALID_OPTION_MESSAGE);
        }
    }

    private void createTeam() throws IOException {
        String teamName = input.askString("\nPlease enter the team's name: ");
        Team t = teamManager.getTeamByName(teamName);
        ArrayList<Member> members;

        if (t == null) {
            members = memberManager.getMembers();
            Team team = new Team(teamName, members);
            teamManager.addTeam(team);
            statsManager.inicialiceStats(teamName);
        } else {
            Output.printPhrase("\nWe are sorry \"" + t.getName() + "\" is taken.");
        }
    }

    private void listTeams() {
        int option;

        do {
            output.listMenu(teamManager.listTeams());
            option = input.askInteger(CHOOSE_OPTION_MESSAGE);

            if (option != 0) {
                try {
                    Team team = teamManager.getTeamByIndex(option);
                    output.listTeamAttributes(team, characterManager.getCharactersOfTeam(team), statsManager.getStatsByIndex(option));
                    input.pressAnyKeyToContinue();
                } catch (Exception e) {
                    Output.printPhrase(TEAM_NO_EXIST_MESSAGE);
                    input.pressAnyKeyToContinue();
                }
            }
        } while (option != 0);
    }

    private void deleteTeam() throws IOException {
        String teamName;
        teamName = input.askString("\n\tEnter the name of the team to remove: ");

        if (input.askString("\n\tAre you sure you want to delete this team ? ").equals("Yes")) {
            Output.printPhrase(teamManager.deleteTeamByName(teamName));
        } else {
            Output.printPhrase("Team doesn't delete");
        }
    }

    private void listCharacters() {
        int option;

        do {
            output.listMenu(characterManager.listCharacters());
            option = input.askInteger(CHOOSE_OPTION_MESSAGE);

            if (option != 0) {
                try {
                    output.listCharacterAttributes(characterManager.listCharacterAttribute(option), teamManager.listTeamsOfCharacter(option));
                    input.pressAnyKeyToContinue();
                } catch (Exception e) {
                    Output.printPhrase("Character doesn't exist");
                    input.pressAnyKeyToContinue();
                }
            }
        } while (option != 0);
    }

    private void manageTeams() throws IOException {
        int option;
        do {
            output.manageTeamsMenu();
            option = input.askInteger("\nChoose an option: ");
            executeManageTeams(OptionManageTeam.convertIntToEnum(option));
        } while (option != 4);
    }

    private void listItems() {
        int option;
        do {
            output.listMenu(itemManager.listItems());
            option = input.askInteger(CHOOSE_OPTION_MESSAGE);

            if (option != 0) {
                try {
                    output.listItemAttributes(itemManager.listItemAttribute(option));
                    input.pressAnyKeyToContinue();
                } catch (Exception e) {
                    Output.printPhrase("Input doesn't exist");
                    input.pressAnyKeyToContinue();
                }
            }
        } while (option != 0);

    }

    private void executeBattle(Battle battle) {
        Output.printPhrase("Combat ready!");
        input.pressAnyKeyToContinue();
        BattleManager battleManager = new BattleManager(battle);
        battleManager.executeBattle();
    }

    private void initializeBattle (ArrayList<Team> teams) {
        Output.printPhrase(INITIALIZING_TEAMS_MESSAGE);

        for (Team team : teams) {
            for (int j = 0; j < 4; j++) {
                Item randomWeapon = itemManager.getRandomWeapon();
                Item randomArmor = itemManager.getRandomArmor();
                Member member = team.getMembers().get(j);

                if (randomArmor == null) {
                    j--;
                }
                if (randomWeapon == null) {
                    j--;
                }

                member.setArmor(randomArmor);
                member.setWeapon(randomWeapon);

                String memberID = String.valueOf(member.getId()) ;
                member.setName(characterManager.getCharacterByIdOrName(memberID).getName());
                member.setDamageReceived(0);
            }
        }

        Battle battle = new Battle(teams);
        System.out.println(battle);
        executeBattle(battle);
    }

    private ArrayList<Team> selectTeamsForBattle() {
        ArrayList<Team> teams = new ArrayList<>();
        Output.printPhrase(LOOKING_AVAILABLE_TEAMS);

        ArrayList<String> teamsName = teamManager.listTeams();
        output.listTeamsNames(teamsName);

        for (int i = 1; i <= 2; i++) {
            int teamIndex = input.askInteger("Choose team #" + i + ": ");
            try {
                Team team = teamManager.getTeamByIndex(teamIndex);
                teams.add(team);
            } catch (Exception e) {
                Output.printPhrase(TEAM_NO_EXIST_MESSAGE);
                i--;
            }
        }

        return teams;
    }

    private void simulateCombat() {
        Output.printPhrase(STARTING_SIMULATION_MESSAGE);
        ArrayList<Team> teams = selectTeamsForBattle();
        initializeBattle(teams);
    }

    public void run() throws IOException {
        int option;
        do {
            output.mainMenu();
            option = input.askInteger(CHOOSE_OPTION_MESSAGE);
            executeMainOption(OptionStartingMenu.convertIntToEnum(option));
        } while (option != 5);
    }

    public static void main(String[] args) throws IOException {
        new Controller().run();
    }
}