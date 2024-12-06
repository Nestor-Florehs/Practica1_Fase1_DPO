package Business;

import Presentation.Output;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BattleManager {

    private Battle battle;

    public BattleManager(Battle battle) {
        this.battle = battle;
    }

    private void showStateOfBattlePerTurn(int turn) {
        Output.printPhrase("\n--- ROUND " + turn + "! ---");
        int teamIndex = 1;
        for (Team team : battle.getTeams()) {
            Output.printPhrase("\nTeam #" + teamIndex + " - " + team.getName());
            ArrayList<Member> members = team.getMembers();
            for (Member member : members) {
                if (member.getWeapon() == null && member.getArmor() == null) {
                    Output.printPhrase("\t- " + member.getName() + member.getPercentageOfDamage() + " No Weapon" + " - " + "No Armor");
                } else if (member.getWeapon() == null && member.getArmor() != null) {
                    Output.printPhrase("\t- " + member.getName() + member.getPercentageOfDamage() + " No Weapon" + " - " + member.getArmor().getName());
                } else if (member.getWeapon() != null && member.getArmor() == null) {
                    Output.printPhrase("\t- " + member.getName() + member.getPercentageOfDamage() + member.getWeapon().getName() + " - " + "No Armor");
                } else {
                    Output.printPhrase("\t- " + member.getName() + member.getPercentageOfDamage() + member.getWeapon().getName() + " - " + member.getArmor().getName());
                }
            }
            teamIndex++;
        }
    }

    private Member getRandomMemberOfRivalTeam(Team currentTeam) {
        // Obtener todos los equipos
        ArrayList<Team> teams = battle.getTeams();

        // Crear una lista de rivales
        ArrayList<Member> rivalMembers = new ArrayList<>();
        for (Team team : teams) {
            if (!team.equals(currentTeam)) { // Excluir al equipo actual
                rivalMembers.addAll(team.getMembers()); // Añadir miembros del equipo rival
            }
        }

        // Seleccionar un miembro aleatorio
        int randomIndex = (int) (Math.random() * rivalMembers.size());
        return rivalMembers.get(randomIndex);

    }

    private void executeTurn() {
        System.out.println();
        for (Team team : battle.getTeams()) {
            ArrayList<Member> members = team.getMembers();
            for (Member member : members) {
                String strategy = member.getStrategy();

                if (strategy.equals("balanced")) {
                    if (member.getWeapon() == null) {
                        System.out.println(member.getName() + " coge una arma");
                        member.setWeapon(new ItemManager().getRandomWeapon());
                    } else {
                        if (member.getArmor() != null) {
                            if (member.getDamageReceived() > 0.5 && member.getDamageReceived() < 1.0) {
                                System.out.println(member.getName() + " defence");
                            } else {
                                Member defender = getRandomMemberOfRivalTeam(team);
                                battle.attack(member, defender);
                            }
                        } else {
                            Member defender = getRandomMemberOfRivalTeam(team);
                            battle.attack(member, defender);
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    public void showBreaksItems() {
        System.out.println();
        for (Team team : battle.getTeams()) {
            ArrayList<Member> members = team.getMembers();
            for (Member member : members) {
                if (member.getWeapon() != null) {
                    if (member.getWeapon().getDurability() <= 0) {
                        System.out.println("Oh no!" +  member.getName() + "’s " + member.getWeapon().getName() + " breaks!");
                        member.setWeapon(null);
                    }
                }
                if (member.getArmor() != null) {
                    if (member.getArmor().getDurability() <= 0) {
                        System.out.println("Oh no!" +  member.getName() + "’s " + member.getArmor().getName() + " breaks!");
                        member.setArmor(null);
                    }
                }
            }
            System.out.println();
        }
    }

    public void executeBattle() {
        boolean combatEnd = false;
        int turn = 1;

        do {
            showStateOfBattlePerTurn(turn);
            executeTurn();
            showBreaksItems();

            if (turn == 2) {
                combatEnd = true;
            }
            turn++;
        } while (!combatEnd);

    }
}
