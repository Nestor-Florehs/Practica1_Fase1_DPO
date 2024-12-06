package Business;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Member> members;

    public Team(String name, ArrayList<Member> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Long> getCharactersIDs() {
        ArrayList<Long> charactersIDs = new ArrayList<>();
        for (Member member : members) {
            charactersIDs.add(member.getId());
        }
        return charactersIDs;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public String toString() {
        return name + " " + members;
    }
}
