package model;

import java.util.List;

public class SuiteRoom extends Room {
    public SuiteRoom(String number) {
        super(number, 250.0);
    }

    @Override
    public String getType() {
        return "Suite";
    }

    @Override
    public List<String> getAmenities() {
        return List.of("Jacuzzi", "Balcony", "King Bed", "Room Service");
    }

    @Override
    public double calculateFinalPrice(int nights) {
        double discount = (nights >= 5) ? 0.9 : 1.0;
        return basePrice * nights * discount;
    }

    @Override
    public double getCancellationFee(int daysBeforeCheckIn) {
        return 50.0;
    }
}

