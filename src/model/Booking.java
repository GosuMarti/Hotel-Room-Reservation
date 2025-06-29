package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private String id;
    private String roomNumber;
    private String username;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double totalPrice;

    public Booking(String id, String username, String roomNumber, LocalDate checkIn, LocalDate checkOut, double totalPrice) {
        this.id = id;
        this.username = username;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }

    public long getNights() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s,%.2f",
                id, username, roomNumber, checkIn, checkOut, totalPrice);
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
