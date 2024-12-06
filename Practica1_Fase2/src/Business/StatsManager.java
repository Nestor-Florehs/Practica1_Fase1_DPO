package Business;

import Persistence.StatsDao;

public class StatsManager {

    /*
    public Stats getStatsByIndex(int index) {

    }

     */

    public Stats getStatsByIndex (int index) {
        StatsDao dao = new StatsDao();
        return dao.getStatsByIndex(index);
    }

    public void inicialiceStats (String teamName) {
        StatsDao dao = new StatsDao();
        Stats stats = new Stats(0, 0, 0, 0);
        dao.addStats(stats, teamName);
    }
}
