package model;

import java.util.List;

public class DeluxeRoom extends Room {
    public DeluxeRoom(String number) {
        super(number, 120.0);
    }

    @Override
    public String getType() {
        return "Deluxe";
    }

    @Override
    public List<String> getAmenities() {
        return List.of("TV", "Minibar", "City View");
    }

    @Override
    public double calculateFinalPrice(int nights) {
        return basePrice * nights;
    }

    @Override
    public double getCancellationFee(int daysBeforeCheckIn) {
        return daysBeforeCheckIn < 2 ? 30.0 : 10.0;
    }
}

