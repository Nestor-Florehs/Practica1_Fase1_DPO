package Presentation;

import java.util.ArrayList;

import Business.*;
import Business.Character;

public class Output {

    public void mainMenu(){
        System.out.println("\n\t1) List Characters");
        System.out.println("\t2) Manage Teams");
        System.out.println("\t3) List Items");
        System.out.println("\t4) Simulate Combat");
        System.out.println("\n\t5) Exit");
    }

    public static void printPhrase(String phrase) {
        System.out.println(phrase);
    }

    public void listMenu(ArrayList<String> strings){
        int i = 0;
        System.out.println();
        for (String phrase : strings) {
            i++;
            System.out.println("\t" + i + ")" + phrase);
        }
        System.out.println();

        System.out.println("\t0) Back");
    }

    public void listCharacterAttributes(Character character, ArrayList<String> teamsNames){
        System.out.println(character.toString());
        System.out.println("\tTEAMS:");
        if (teamsNames.size() > 0) {
            for (String name : teamsNames){
                System.out.println("\t\t" + "-" + name);
            }
        } else {
            System.out.println("\t\t" + "-No teams");
        }
    }

    public void listItemAttributes(Item item){
        System.out.println(item);
    }

    public void manageTeamsMenu(){
        System.out.println("\nTeam management.");
        System.out.println("\t1) Create a Team");
        System.out.println("\t2) List Teams");
        System.out.println("\t3) Delete a Team");
        System.out.println("\n\t4) Back");
    }

    public void enterTeamNameToCreate(){
        System.out.println("Please enter the teams’s name: ");
    }

    public void enterTeamMemberName(int memberNumber){
        System.out.println("Please enter name or id for character #" + memberNumber + ":");
    }


    public void listTeamAttributes (Team team, ArrayList<Member> members, Stats teamStats){
        System.out.println("\nTeam Name: " + team.getName() + "\n");
        int i = 0;

        for (Member member : members){
            i++;
            System.out.println("Character #" + i + ": " + member.getName() + "\t(" + member.getStrategy() + ")");
        }

        System.out.println("\nCombats played:\t" + teamStats.getGamesPlayed());
        System.out.println("Combats won:\t" + teamStats.getGamesWon());
        if (teamStats.getGamesPlayed() == 0) {
            System.out.println("Win rate:\tN/A (no games played)");
        } else {
            System.out.println("Win rate:\t" + ((teamStats.getGamesWon() * 100) / teamStats.getGamesPlayed()) + "%");
        }
        System.out.println("KOs done:\t" + teamStats.getKODone());
        System.out.println("KOs received:\t" + teamStats.getKOReceived());
    }


    /*
    public void listTeamMemberStrategies(int memberNumber, Strategies strategy){
        int i;
        System.out.println("Game strategy for character #" + memberNumber + "?");
        for(i = 1; i < 2; i++) {
            System.out.println(i + ") " + strategy.getName());
        }
    }
     */

    public void teamCreated(boolean created, String teamName){
        if(created){
            System.out.println("Team " + teamName + "has been successfully created!\n");
        }else{
            System.out.println("Team has not been created!\n");
        }
    }

    /*
    public void listAvailableTeams(ArrayList<Team> teams){
        int i = 0;
        System.out.println();
        for (Team team : teams) {
            i++;
            System.out.println("\t" + i + ")" + team);
        }
        System.out.println();

        System.out.println("\t0) Back");
    }
     */

    public void enterTeamNameToRemove(){
        System.out.println("Enter the name of the team to remove: ");
    }

    public void removeTeam(String teamName){
        System.out.println("Are you sure you want to remove \"" + teamName + "\" ? ");
    }

    public void teamRemoved(boolean removed, String teamName){
        if(removed){
            System.out.println("\"" + teamName + "\" has been removed from the system.");
        }
    }

    /*
    public void listItemsMenu(ArrayList<Item> items){
        int i = 0;
        System.out.println();
        for (Item item : items) {
            i++;
            System.out.println("\t" + i + ")" + item);
        }
        System.out.println();

        System.out.println("\t0) Back");
    }

    public void listItemsAttributes(Item item){
        System.out.println(item.toString());
        System.out.println("<Press any key to continue...>");
    }
    */

    public void listTeamsNames(ArrayList<String> teamsNames){
        int i = 0;
        System.out.println();
        for (String teamName : teamsNames) {
            i++;
            System.out.println("\t" + i + ")" + teamName);
        }
        System.out.println();
    }

}