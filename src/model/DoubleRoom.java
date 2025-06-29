package model;

import java.util.List;

public class DoubleRoom extends Room {

    public DoubleRoom(String number) {
        super(number, 100.0);
    }

    @Override
    public String getType() {
        return "Double";
    }

    @Override
    public List<String> getAmenities() {
        return List.of("Two Beds", "TV", "Wi-Fi");
    }

    @Override
    public double calculateFinalPrice(int nights) {
        return basePrice * nights;
    }

    @Override
    public double getCancellationFee(int daysBeforeCheckIn) {
        if (daysBeforeCheckIn < 2) return 25.0;
        else return 10.0;
    }
}

