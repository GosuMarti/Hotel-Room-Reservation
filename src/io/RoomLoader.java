package io;

import factory.RoomFactory;
import model.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomLoader {

    public static List<Room> loadRooms(String filePath) {
        List<Room> rooms = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
            System.err.println("Error reading rooms file: " + e.getMessage());
        }
        return rooms;
    }
}

