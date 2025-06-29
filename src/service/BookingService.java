package service;

import io.RoomRepository;
import model.Booking;
import model.Room;
import model.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import static utils.DateFormater.DISPLAY_FORMAT;

public class BookingService {
    private final List<Room> allRooms;
    private final UserService userService;
    private final Scanner scanner;

    public BookingService(List<Room> rooms, UserService userService, Scanner scanner) {
        this.allRooms = rooms;
        this.userService = userService;
        this.scanner = scanner;
    }

    public void startBookingFlow() {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        System.out.println("=== Start Booking ===");
        System.out.print("Enter room type (Single, Double, Deluxe, Suite): ");
        String type = scanner.nextLine().trim();

        System.out.print("Enter check-in date (dd-MM-yyyy): ");
        LocalDate checkIn = readDate();

        System.out.print("Enter check-out date (dd-MM-yyyy): ");
        LocalDate checkOut = readDate();

        if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
            System.out.println("Check-out date must be after check-in.");
            return;
        }

        List<Room> available = findAvailableRooms(type);
        if (available.isEmpty()) {
            System.out.println("No available rooms of that type.");
            return;
        }

        System.out.println("Available rooms:");
        for (int i = 0; i < available.size(); i++) {
            Room r = available.get(i);
            System.out.printf("%d. Room %s | Price: %.2f | Amenities: %s\n",
                    i + 1, r.getNumber(), r.calculateFinalPrice((int) daysBetween(checkIn, checkOut)), r.getAmenities());
        }

        System.out.print("Select room number (1-" + available.size() + "): ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= available.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Room selected = available.get(index);
        long nights = daysBetween(checkIn, checkOut);
        double totalPrice = selected.calculateFinalPrice((int) nights);
        String bookingId = UUID.randomUUID().toString();

        Booking booking = new Booking(
                bookingId,
                currentUser.getUsername(),
                selected.getNumber(),
                checkIn,
                checkOut,
                totalPrice
        );

        selected.setBooked(true);
        currentUser.addBooking(booking);

        userService.saveUsersToFile();
        RoomRepository.saveRooms(allRooms);

        System.out.println("Booking successful!");
        System.out.printf("Booking ID: %s | Total: %.2f\n", bookingId, totalPrice);
    }

    private LocalDate readDate() {
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine().trim(), DISPLAY_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.print("Invalid format. Try again (dd-MM-yyyy): ");
            }
        }
    }

    private long daysBetween(LocalDate start, LocalDate end) {
        return end.toEpochDay() - start.toEpochDay();
    }

    private List<Room> findAvailableRooms(String type) {
        List<Room> result = new ArrayList<>();
        for (Room r : allRooms) {
            if (!r.isBooked() && r.getType().equalsIgnoreCase(type)) {
                result.add(r);
            }
        }
        return result;
    }
}

