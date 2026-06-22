package com.codesui.footballlatest.data;

public class MatchSubstitution {
    private final int minute;
    private final TeamInfo team;
    private final PlayerInfo playerOut;
    private final PlayerInfo playerIn;

    public MatchSubstitution(int minute, TeamInfo team, PlayerInfo playerOut, PlayerInfo playerIn) {
        this.minute = minute;
        this.team = team;
        this.playerOut = playerOut;
        this.playerIn = playerIn;
    }

    public int getMinute() {
        return minute;
    }

    public TeamInfo getTeam() {
        return team;
    }

    public PlayerInfo getPlayerOut() {
        return playerOut;
    }

    public PlayerInfo getPlayerIn() {
        return playerIn;
    }

    public static class TeamInfo {
        private final int id;
        private final String name;

        public TeamInfo(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class PlayerInfo {
        private final int id;
        private final String name;

        public PlayerInfo(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
