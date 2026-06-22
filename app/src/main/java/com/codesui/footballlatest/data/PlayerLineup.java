package com.codesui.footballlatest.data;

public class PlayerLineup {
    private final int id;
    private final String name;
    private final String position;
    private final int shirtNumber;

    public PlayerLineup(int id, String name, String position, int shirtNumber) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.shirtNumber = shirtNumber;
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
}
