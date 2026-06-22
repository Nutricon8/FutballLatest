package com.codesui.footballlatest.data;

public class SquadPlayer {
    private final int id;
    private final String name;
    private final String position;
    private final int shirtNumber;
    private final String nationality;
    private final String dateOfBirth;

    public SquadPlayer(int id, String name, String position, int shirtNumber, String nationality, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.shirtNumber = shirtNumber;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPositionShort() {
        switch (position) {
            case "Goalkeeper":
                return "GK";
            case "Defender":
                return "DEF";
            case "Midfielder":
                return "MID";
            case "Attacker":
                return "ATT";
            default:
                return position;
        }
    }
}
