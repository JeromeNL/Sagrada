package model;

public class PlayerStats {
    private String username;
    private int playerId;
    private int highscore;
    private int mostUsedDieValue;
    private String mostUsedDieColor;
    private int oponentCount;
    private int wins;
    private int losses;

    public PlayerStats(String username, int playerId, int highscore, int mostUsedDieValue, String mostUsedDieColor, int oponentCount, int wins, int losses) {
        this.username = username;
        this.playerId = playerId;
        this.highscore = highscore;
        this.mostUsedDieValue = mostUsedDieValue;
        this.mostUsedDieColor = mostUsedDieColor;
        this.oponentCount = oponentCount;
        this.wins = wins;
        this.losses = losses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getMostUsedDieValue() {
        return mostUsedDieValue;
    }

    public void setMostUsedDieValue(int mostUsedDieValue) {
        this.mostUsedDieValue = mostUsedDieValue;
    }

    public String getMostUsedDieColor() {
        return mostUsedDieColor;
    }

    public void setMostUsedDieColor(String mostUsedDieColor) {
        this.mostUsedDieColor = mostUsedDieColor;
    }

    public int getOponentCount() {
        return oponentCount;
    }

    public void setOponentCount(int oponentCount) {
        this.oponentCount = oponentCount;
    }
}
