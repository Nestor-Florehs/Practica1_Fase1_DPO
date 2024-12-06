package Business;

public class Character {
    private long id;
    private String name;
    private int weight;

    public Character(long id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public String toString() {
        String characterAttributes;

        characterAttributes = "\n\tID: " + "\t" + id;
        characterAttributes += "\n\tNAME: " + "\t" + name;
        characterAttributes += "\n\tWEIGHT: " + "\t" + weight;

        return characterAttributes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
