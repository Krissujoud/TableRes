package com.example.resto.model;

import jakarta.persistence.*;

@Entity
@jakarta.persistence.Table(name = "restaurant_table")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int seats;

    @Enumerated(EnumType.STRING)
    private Zone zone;

    private int x;
    private int y;

    private boolean windowSeat;
    private boolean quiet;
    private boolean nearKids;
    private boolean accessible;

    public Table() {}

    public Table(String name, int seats, Zone zone, int x, int y) {
        this.name = name;
        this.seats = seats;
        this.zone = zone;
        this.x = x;
        this.y = y;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getSeats() { return seats; }
    public Zone getZone() { return zone; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isWindowSeat() { return windowSeat; }
    public boolean isQuiet() { return quiet; }
    public boolean isNearKids() { return nearKids; }
    public boolean isAccessible() { return accessible; }
}