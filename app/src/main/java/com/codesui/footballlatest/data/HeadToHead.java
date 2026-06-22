package com.codesui.footballlatest.data;

public class HeadToHead {
    private final int numberOfMatches;
    private final int totalGoals;
    private final TeamStats homeTeam;
    private final TeamStats awayTeam;

    public HeadToHead(int numberOfMatches, int totalGoals, TeamStats homeTeam, TeamStats awayTeam) {
        this.numberOfMatches = numberOfMatches;
        this.totalGoals = totalGoals;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public TeamStats getHomeTeamStats() {
        return homeTeam;
    }

    public TeamStats getAwayTeamStats() {
        return awayTeam;
    }

    public static class TeamStats {
        private final int wins;
        private final int draws;
        private final int losses;

        public TeamStats(int wins, int draws, int losses) {
            this.wins = wins;
            this.draws = draws;
            this.losses = losses;
        }

        public int getWins() {
            return wins;
        }

        public int getDraws() {
            return draws;
        }

        public int getLosses() {
            return losses;
        }
    }
}
