package model;

import java.util.List;

public abstract class Room {
    protected String number;
    protected double basePrice;
    protected boolean isBooked;

    public Room(String number, double basePrice) {
        this.number = number;
        this.basePrice = basePrice;
        this.isBooked = false;
    }

    public abstract String getType();

    public abstract List<String> getAmenities();

    public abstract double calculateFinalPrice(int nights);

    public abstract double getCancellationFee(int daysBeforeCheckIn);

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

}

