package Persistence;

import Business.Character;
import Business.Member;
import Presentation.Input;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Business.Team;

public class TeamDao {
    private static final String FILE_PATH = "C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\Practica-1-DPO\\src\\Persistence\\Database\\teams.json";

    private ArrayList<Team> getAllTeams() {
        JSONParser parser = new JSONParser();
        ArrayList<Team> teams = new ArrayList<>();

        try {
            // Check if the file exists
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.err.println("The file does not exist, returning an empty list.");
                return teams;
            }

            // Load the JSON file
            FileReader reader = new FileReader(FILE_PATH);

            // Parse the JSON file as an array
            JSONArray array = (JSONArray) parser.parse(reader);

            // Iterate through each JSON object in the array
            for (Object o : array) {
                ArrayList<Member> membersList = new ArrayList<>();
                JSONObject team = (JSONObject) o;
                JSONArray members = (JSONArray) team.get("members");

                // Extract fields
                String name = (String) team.get("name");
                for (Object o2 : members) {
                    JSONObject member = (JSONObject) o2;
                    long id = (long) member.get("id"); // Handle long for large numbers
                    String strategy = (String) member.get("strategy"); // Handle strategy field
                    membersList.add(new Member(id, strategy));
                }
                Team t = new Team(name, membersList);
                teams.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teams;
    }


    public void saveTeams(ArrayList<Team> teams) throws IOException {
        JSONArray teamArray = new JSONArray();

        for (Team team : teams) {
            JSONObject teamObject = new JSONObject();
            teamObject.put("name", team.getName());

            // Crear un JSONArray para los miembros
            JSONArray membersArray = new JSONArray();
            for (Member member : team.getMembers()) {
                JSONObject memberObject = new JSONObject();
                memberObject.put("id", member.getId());
                memberObject.put("strategy", member.getStrategy());
                membersArray.add(memberObject);
            }

            teamObject.put("members", membersArray); // Añadir los miembros al equipo
            teamArray.add(teamObject);
        }

        // Guardar en el archivo
        File file = new File(FILE_PATH);
        try (FileWriter writeTeams = new FileWriter(file)) {
            writeTeams.write(teamArray.toJSONString());
            writeTeams.flush();
        }
    }


    public Team getTeamByName(String name) {
        ArrayList<Team> teams = getAllTeams();
        for (Team t : teams) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public String deleteTeamByName(String name) throws IOException {
        Team t = getTeamByName(name);
        ArrayList<Team> teams = getAllTeams();
        boolean found = false;

        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getName().equals(name)) {
                found = true;
                teams.remove(i);
            }
        }
        saveTeams(teams);

        if (found) {
            return "\n\t(" + name + ")" + " has been removed from the system.";
        } else {
            return "\n\t(" + name + ")" + " has not exist in the system.";
        }
    }

    public ArrayList<String> getAllTeamNames() {
        ArrayList<String> teamNames = new ArrayList<>();
        ArrayList<Team> teams = getAllTeams();
        for (Team team : teams) {
            teamNames.add(team.getName());
        }
        return teamNames;
    }

    public ArrayList<String> getTeamsOfCharacter(long id) {
        ArrayList<Team> teams = getAllTeams();
        ArrayList<String> teamNames = new ArrayList<>();
        boolean found;

        for (Team team : teams) {
            ArrayList<Long> membersIDs = team.getCharactersIDs();
            found = false;

            for (Long memberID : membersIDs) {
                if (memberID == id && !found) {
                    teamNames.add(team.getName());
                    found = true;
                }
            }
        }

        return teamNames;
    }

    public Team getTeamByIndex(int index) {
        ArrayList<Team> teams = getAllTeams();
        return teams.get(index - 1);
    }

    public void addTeam(Team team) throws IOException {
        ArrayList<Team> teams = getAllTeams();
        teams.add(team);
        saveTeams(teams);
    }

}
