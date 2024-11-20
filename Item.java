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
        return id + " " + name + " " + power + " " + durability + " " + type;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
