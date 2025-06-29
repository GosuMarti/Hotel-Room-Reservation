package io;

import factory.RoomFactory;
import model.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private static final String ROOM_FILE = "src/data/rooms.csv";

    public static List<Room> loadRooms() {
        List<Room> rooms = new ArrayList<>();
        File file = new File(ROOM_FILE);
        if (!file.exists()) return rooms;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length < 3) continue;

                String number = tokens[0].trim();
                String type = tokens[1].trim();
                boolean isBooked = Boolean.parseBoolean(tokens[2].trim());

                Room room = RoomFactory.createRoom(type, number, isBooked);
                rooms.add(room);
            }
        } catch (IOException e) {
            System.err.println("Failed to load rooms: " + e.getMessage());
        }

        return rooms;
    }

    public static void saveRooms(List<Room> rooms) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ROOM_FILE))) {
            for (Room room : rooms) {
                writer.write(String.format("%s,%s,%b\n",
                        room.getNumber(),
                        room.getType(),
                        room.isBooked()));
            }
        } catch (IOException e) {
            System.err.println("Failed to save rooms: " + e.getMessage());
        }
    }
}
