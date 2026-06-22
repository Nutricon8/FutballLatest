package com.codesui.footballlatest.data;

public class MatchBooking {
    private final int minute;
    private final TeamInfo team;
    private final PlayerInfo player;
    private final String card;

    public MatchBooking(int minute, TeamInfo team, PlayerInfo player, String card) {
        this.minute = minute;
        this.team = team;
        this.player = player;
        this.card = card;
    }

    public int getMinute() {
        return minute;
    }

    public TeamInfo getTeam() {
        return team;
    }

    public PlayerInfo getPlayer() {
        return player;
    }

    public String getCard() {
        return card;
    }

    public boolean isRedCard() {
        return "RED_CARD".equals(card) || "YELLOW_RED_CARD".equals(card);
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
