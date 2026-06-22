package com.codesui.footballlatest.data;

import org.json.JSONObject;

public class MatchEvent {
    private final int minute;
    private final Integer extraTime;
    private final String type;
    private final TeamInfo team;
    private final ScorerInfo scorer;
    private final AssistInfo assist;

    public MatchEvent(int minute, Integer extraTime, String type, TeamInfo team, ScorerInfo scorer, AssistInfo assist) {
        this.minute = minute;
        this.extraTime = extraTime;
        this.type = type;
        this.team = team;
        this.scorer = scorer;
        this.assist = assist;
    }

    public int getMinute() {
        return minute;
    }

    public Integer getExtraTime() {
        return extraTime;
    }

    public String getType() {
        return type;
    }

    public TeamInfo getTeam() {
        return team;
    }

    public ScorerInfo getScorer() {
        return scorer;
    }

    public AssistInfo getAssist() {
        return assist;
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

    public static class ScorerInfo {
        private final int id;
        private final String name;

        public ScorerInfo(int id, String name) {
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

    public static class AssistInfo {
        private final int id;
        private final String name;

        public AssistInfo(int id, String name) {
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
