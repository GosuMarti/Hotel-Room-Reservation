package model;

import java.util.List;

public class SingleRoom extends Room {

    public SingleRoom(String number) {
        super(number, 80.0);
    }

    @Override
    public String getType() {
        return "Single";
    }

    @Override
    public List<String> getAmenities() {
        return List.of("Desk", "Wi-Fi", "Shower");
    }

    @Override
    public double calculateFinalPrice(int nights) {
        return basePrice * nights;
    }

    @Override
    public double getCancellationFee(int daysBeforeCheckIn) {
        return daysBeforeCheckIn < 1 ? 15.0 : 5.0;
    }
}

