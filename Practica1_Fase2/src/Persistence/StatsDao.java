package Persistence;

import Business.Stats;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public class StatsDao {
    private static final String FILE_PATH = "C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\Practica-1-DPO\\src\\Persistence\\Database\\stats.json";

    private ArrayList<Stats> getAllStats() {
        JSONParser parser = new JSONParser();
        ArrayList<Stats> stats = new ArrayList<>();

        try {
            // Verifica si el archivo existe antes de intentar leer
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.err.println("El archivo no existe, devolviendo una lista vacía.");
                return stats;
            }

            // Carga y analiza el archivo JSON
            FileReader reader = new FileReader(FILE_PATH);
            JSONArray array = (JSONArray) parser.parse(reader);

            // Itera sobre cada objeto JSON en el array
            for (Object o : array) {
                JSONObject stat = (JSONObject) o;

                // Convierte los valores numéricos de Long a int
                int gamesPlayed = ((Long) stat.get("games_played")).intValue();
                int gamesWon = ((Long) stat.get("games_won")).intValue();
                int koDone = ((Long) stat.get("KO_done")).intValue();
                int koReceived = ((Long) stat.get("KO_received")).intValue();

                // Crea un objeto Stats y lo añade a la lista
                Stats s = new Stats(gamesPlayed, gamesWon, koDone, koReceived);
                stats.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stats;
    }

    public void saveStats(ArrayList<Stats> stats, String teamName) {
        JSONArray statsArray = new JSONArray();

        for (Stats s : stats) {
            JSONObject stat = new JSONObject();
            stat.put("games_played", s.getGamesPlayed());
            stat.put("games_won", s.getGamesWon());
            stat.put("KO_done", s.getKODone());
            stat.put("KO_received", s.getKOReceived());

            statsArray.add(stat);
        }

        File file = new File(FILE_PATH);
        try (FileWriter writeTeams = new FileWriter(file)) {
            writeTeams.write(statsArray.toJSONString());
            writeTeams.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stats getStatsByIndex(int index) {
        return getAllStats().get(index - 1);
    }

    public void addStats(Stats s, String teamName) {
       ArrayList<Stats> stats = getAllStats();
       stats.add(s);
       saveStats(stats, teamName);
    }
}
