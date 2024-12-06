package Persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Business.Member;
import Business.Team;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Business.Character;

public class CharacterDao {
    private static final String FILE_PATH = "C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\Practica-1-DPO\\src\\Persistence\\Database\\characters.json";

    private ArrayList<Character> getAllCharacters() {
        JSONParser parser = new JSONParser();
        ArrayList<Character> characters = new ArrayList<>();

        try {
            // Verifica si el archivo existe antes de intentar leer
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.err.println("El archivo no existe, devolviendo una lista vacía.");
                return characters;
            }

            // Load the JSON file
            FileReader reader = new FileReader(FILE_PATH);

            // Parse the JSON file as an array
            JSONArray array = (JSONArray) parser.parse(reader);

            // Iterate through each JSON object in the array
            for (Object o : array) {
                JSONObject character = (JSONObject) o;

                // Extract fields
                long id = (long) character.get("id");
                String name = (String) character.get("name");
                long weightLong = (long) character.get("weight");

                int weight = (int) weightLong;

                Character c = new Character(id, name, weight);
                characters.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return characters;
    }

    public static void saveCharacter(ArrayList<Character> characters) throws IOException {
        JSONArray characterArray = new JSONArray();

        for (Character participant : characters) {
            JSONObject characterObject = new JSONObject();
            characterObject.put("name", participant.getName());
            characterObject.put("weight", participant.getWeight());
            characterObject.put("id", participant.getId());

            characterArray.add(characterObject);
        }

        File file = new File(FILE_PATH);

        FileWriter writerParticipants = new FileWriter(file);
        writerParticipants.write(characterArray.toJSONString());
        writerParticipants.flush();
        writerParticipants.close();
    }

    public ArrayList<String> getAllCharactersName() {
        ArrayList<String> charactersName = new ArrayList<>();
        ArrayList<Character> characters = getAllCharacters();
        for (Character character : characters) {
            charactersName.add(character.getName());
        }
        return charactersName;
    }

    public Character getCharacter(int index) {
        ArrayList<Character> characters = getAllCharacters();
        return characters.get(index - 1);
    }

    public ArrayList<Member> getCharactersOfTeam(Team team) {
        ArrayList<Character> characters = getAllCharacters();
        ArrayList<Member> members = team.getMembers();
        ArrayList<Member> enrichedMembers = new ArrayList<>();

        for (Member member : members) {
            for (Character character : characters) {
                if (character.getId() == member.getId()) {
                    // Create a new Member with additional details and add it to the result list
                    enrichedMembers.add(new Member(member.getId(), member.getStrategy()));
                    break; // Break the inner loop once a match is found
                }
            }
        }

        return enrichedMembers;
    }


    public Character getCharacterById(long id) {
        ArrayList<Character> characters = getAllCharacters();

        for (Character character : characters) {
            if (character.getId() == id) {
                return character;
            }
        }
        return null;
    }

    public Character getCharacterByName(String name) {
        ArrayList<Character> characters = getAllCharacters();
        for (Character character : characters) {
            if (character.getName().equals(name)) {
                return character;
            }
        }
        return null;
    }
}
