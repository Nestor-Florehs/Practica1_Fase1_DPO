package Persistence;

import Business.Character;
import Business.Item;
import Business.Team;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ItemDao {
    private static final String FILE_PATH = "files/items.json";

    private ArrayList<Item> getAllItems() {
        JSONParser parser = new JSONParser();
        ArrayList<Item> items = new ArrayList<>();

        try {
            // Verifica si el archivo existe antes de intentar leer
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.err.println("El archivo no existe, devolviendo una lista vacía.");
                return items;
            }

            // Load the JSON file
            FileReader reader = new FileReader(FILE_PATH);

            // Parse the JSON file as an array
            JSONArray array = (JSONArray) parser.parse(reader);

            // Iterate through each JSON object in the array
            for (Object o : array) {
                JSONObject item = (JSONObject) o;

                // Extract fields
                long id = (long) item.get("id");
                String name = (String) item.get("name");
                long powerLong = (long) item.get("power");
                long durabilityLong = (long) item.get("power");
                String type = (String) item.get("class");


                int power = (int) powerLong;
                int durability = (int) durabilityLong;


                Item i = new Item(id, name, power, durability, type);
                items.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public ArrayList<String> getAllItemsName() {
        ArrayList<String> itemsName = new ArrayList<>();
        ArrayList<Item> items = getAllItems();
        for (Item item : items) {
            itemsName.add(item.getName());
        }
        return itemsName;
    }

    public Business.Item getItem(int index) {
        ArrayList<Business.Item> items = getAllItems();
        return items.get(index - 1);
    }

}
