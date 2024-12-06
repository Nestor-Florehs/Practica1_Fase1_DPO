package Business;

public class Stats {
    private int gamesPlayed;
    private int gamesWon;
    private int KODone;
    private int KOReceived;

    public Stats(int gamesPlayed, int gamesWon, int KODone, int KOReceived) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.KODone = KODone;
        this.KOReceived = KOReceived;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getKODone() {
        return KODone;
    }

    public int getKOReceived() {
        return KOReceived;
    }

    public String toString() {
        return gamesPlayed + " " + gamesWon + " " + KODone + " " + KOReceived;
    }

}


