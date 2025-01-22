package com.console.app;

public class Movie {
    private int id;
    private String name;
    private String showtime;
    private int availableSeats;
    private double ticketPrice;

    // Constructor
    public Movie(int id, String name, String showtime, int availableSeats, double ticketPrice) {
        this.id = id;
        this.name = name;
        this.showtime = showtime;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShowtime() {
        return showtime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}
