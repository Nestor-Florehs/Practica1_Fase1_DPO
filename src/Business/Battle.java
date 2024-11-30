package Business;

import java.util.ArrayList;

public class Battle {
    private ArrayList<Team> teams;

    public Battle(ArrayList<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("\n");
        for (int i = 1; i <= teams.size(); i++) {
            str.append("\tTeam #").append(i).append(": ").append(teams.get(i - 1).getName()).append("\n");

            ArrayList<Member> members = teams.get(i - 1).getMembers();

            for (Member member : members) {
                str.append("\t- ").append(member.getName()).append("\n");
                str.append("\t\tWeapon: ").append(member.getWeapon().getName()).append("\n");
                str.append("\t\tArmor: ").append(member.getArmor().getName()).append("\n");
            }
            str.append("\n");
        }

        return str.toString();
    }

}
