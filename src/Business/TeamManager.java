package Business;

import Persistence.StatsDao;
import Persistence.TeamDao;

import java.io.IOException;
import java.util.ArrayList;

public class TeamManager {

    public ArrayList<String> listTeams() {
        ArrayList<String> listTeamsName;
        TeamDao teamDao = new TeamDao();
        listTeamsName = teamDao.getAllTeamNames();
        return listTeamsName;
    }

    public ArrayList<String> listTeamsOfCharacter(int index) {
        CharacterManager characterManager = new CharacterManager();
        TeamDao teamDao = new TeamDao();

        Character character = characterManager.listCharacterAttribute(index);
        return teamDao.getTeamsOfCharacter(character.getId());
    }

    public Team getTeamByIndex(int index) {
        Team team;
        TeamDao teamDao = new TeamDao();

        team = teamDao.getTeamByIndex(index);

        return team;
    }

    public String deleteTeamByName(String name) throws IOException {
        StatsDao statsDao = new StatsDao();
        TeamDao teamDao = new TeamDao();

        statsDao.deleteStatsByIndex(getIndexOfTeamByName(name));
        return teamDao.deleteTeamByName(name);
    }

    public Team getTeamByName(String name) {
        TeamDao teamDao = new TeamDao();
        return teamDao.getTeamByName(name);
    }

    public void addTeam(Team team) throws IOException {
        TeamDao teamDao = new TeamDao();
        teamDao.addTeam(team);
    }

    public int getIndexOfTeamByName(String name) {
        TeamDao teamDao = new TeamDao();
        ArrayList<String> teams = teamDao.getAllTeamNames();
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }

}
