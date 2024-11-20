package Presentation;

import Business.Character;
import Business.Item;
import Business.Stats;
import Business.Team;

import java.util.ArrayList;

public class Output {

    public void mainMenu(){
        System.out.println("\n\t1) List Characters");
        System.out.println("\t2) Manage Teams");
        System.out.println("\t3) List Items");
        System.out.println("\t4) Simulate Combat");
        System.out.println("\n\t5) Exit");
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
        for (String name : teamsNames){
            System.out.println("\t\t" + "-" + name);
        }
    }

    public void listTeamAttributes (Team team, ArrayList<String> members, Stats teamStats){
        System.out.println("\nTeam Name: " + team.getName() + "\n");
        int i = 0;

        for (String name : members){
            i++;
            System.out.println("Character #" + i + ": " + name + "\t(BALANCED)");
        }

        System.out.println("\nCombats played:\t" + teamStats.getGamesPlayed());
        System.out.println("Combats won:\t" + teamStats.getGamesWon());
        System.out.println("Win rate:\t" + ((teamStats.getGamesWon() * 100 )/ teamStats.getGamesPlayed()) + "%");
        System.out.println("KOs done:\t" + teamStats.getKODone());
        System.out.println("KOs received:\t" + teamStats.getKOReceived());
    }

    public void listItemAttributes(Item item){
        System.out.println(item.toString());
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

    /*
    public void listTeamMemberStrategies(int memberNumber, Strategies strategy){
        int i;
        System.out.println("Game strategy for character #" + memberNumber + "?");
        for(i = 1; i < 2; i++) {
            System.out.println(i + ") " + strategy.name);
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

    public void listItemsMenu(ArrayList<String> itemsName){
        int i = 0;
        System.out.println();
        for (String itemName : itemsName) {
            i++;
            System.out.println("\t" + i + ")" + itemName);
        }
        System.out.println();

        System.out.println("\t0) Back");
    }

    public void simulateCombatMenu(){

    }
}