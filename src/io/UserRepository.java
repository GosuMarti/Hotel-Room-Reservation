package io;

import model.Booking;
import model.User;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static utils.DateFormater.DISPLAY_FORMAT;

public class UserRepository {
    private static final String USER_FILE = "src/data/users.txt";

    public static Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        File file = new File(USER_FILE);
        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length < 2) continue;

                String username = parts[0];
                String password = parts[1];
                User user = new User(username, password);

                if (parts.length == 3 && !parts[2].isEmpty()) {
                    String[] bookings = parts[2].split(";");
                    for (String b : bookings) {
                        if (b.isBlank()) continue;
                        String[] bParts = b.split(":");
                        if (bParts.length == 5) {
                            Booking booking = new Booking(
                                    bParts[0],
                                    username,
                                    bParts[1],
                                    LocalDate.parse(bParts[2], DISPLAY_FORMAT),
                                    LocalDate.parse(bParts[3], DISPLAY_FORMAT),
                                    Double.parseDouble(bParts[4])
                            );
                            user.addBooking(booking);
                        }
                    }
                }

                users.put(username, user);
            }
        } catch (IOException e) {
            System.err.println("Failed to load users: " + e.getMessage());
        }

        return users;
    }

    public static void saveUsers(Map<String, User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (User user : users.values()) {
                StringBuilder line = new StringBuilder();
                line.append(user.getUsername()).append(",")
                        .append(user.getPassword()).append(",");

                for (Booking booking : user.getBookingHistory()) {
                    line.append(String.format("%s:%s:%s:%s:%.2f;",
                            booking.getId(),
                            booking.getRoomNumber(),
                            booking.getCheckIn().format(DISPLAY_FORMAT),
                            booking.getCheckOut().format(DISPLAY_FORMAT),
                            booking.getTotalPrice()));
                }
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to save users: " + e.getMessage());
        }
    }
}
