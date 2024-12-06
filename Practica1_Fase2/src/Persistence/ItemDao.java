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
import java.util.List;
import java.util.stream.Collectors;

import Business.Item;

public class ItemDao {
    private static final String FILE_PATH = "Practica-1-DPO/src/Persistence/Database/items.json";

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
                long durabilityLong = (long) item.get("durability");
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

    public Item getItem(int index) {
        ArrayList<Item> items = getAllItems();
        return items.get(index - 1);
    }

    public Item getRandomWeapon() {
        ArrayList<Item> items = getAllItems();

        List<Item> weapons = items.stream()
                .filter(n -> n.getType().equals("Weapon"))
                .toList();

        if (weapons.isEmpty()) {
            System.out.println("No weapons found.");
            return null;
        }

        // Seleccionar un arma aleatoria
        Item randomItem = weapons.get((int) (Math.random() * weapons.size()));
        return randomItem;
    }

    public Item getRandomArmor() {
        ArrayList<Item> items = getAllItems();

        List<Item> armor = items.stream()
                .filter(n -> n.getType().equals("Armor"))
                .toList();

        if (armor.isEmpty()) {
            System.out.println("No armors found.");
            return null;
        }

        // Seleccionar un arma aleatoria
        Item randomItem = armor.get((int) (Math.random() * armor.size()));
        return randomItem;
    }


}
