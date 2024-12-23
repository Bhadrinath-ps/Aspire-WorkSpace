package com.console.app;

public class Booking {
    private int bookingId;
    private Movie movie;
    private int tickets;
    private double totalAmount;

    // Constructor for booking
    public Booking(int bookingId, Movie movie, int tickets, double totalAmount) {
        this.bookingId = bookingId;
        this.movie = movie;
        this.tickets = tickets;
        this.totalAmount = totalAmount;
    }

    // Getters
    public int getBookingId() {
        return bookingId;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getTickets() {
        return tickets;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
