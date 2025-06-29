package factory;

import model.*;

public class RoomFactory {

    public static Room createRoom(String type, String number, boolean isBooked) {
        Room room = switch (type.toLowerCase()) {
            case "single" -> new SingleRoom(number);
            case "double" -> new DoubleRoom(number);
            case "deluxe" -> new DeluxeRoom(number);
            case "suite" -> new SuiteRoom(number);
            default -> throw new IllegalArgumentException("Unknown room type: " + type);
        };
        room.setBooked(isBooked);
        return room;
    }
}
