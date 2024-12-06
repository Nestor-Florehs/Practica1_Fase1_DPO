package Business;

public class Item {
    private long id;
    private String name;
    private int power;
    private int durability;
    private String type;

    public Item(long id, String name, int power, int durability, String type) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.durability = durability;
        this.type = type;
    }

    public String toString(){
        String itemAttributes;
        itemAttributes = "\n\tID: " + id;
        itemAttributes += "\n\tNAME: "  + name;
        itemAttributes += "\n\tCLASS: " + type;
        itemAttributes += "\n\tPOWER: " + power;
        itemAttributes += "\n\tDURABILITY: " + durability;
        return itemAttributes;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public int getPower() {
        return power;
    }
    public int getDurability() {
        return durability;
    }

    public void useItem() {
        durability--;
    }
}
