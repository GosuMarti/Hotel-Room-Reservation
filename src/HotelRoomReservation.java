import io.RoomRepository;
import model.Room;
import service.BookingService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class HotelRoomReservation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        List<Room> rooms = RoomRepository.loadRooms();

        BookingService bookingService = new BookingService(rooms, userService, scanner);

        boolean running = true;

        while (running) {
            while (userService.getCurrentUser() == null) {
                System.out.println("\n=== Welcome to the Hotel System ===");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("0. Exit");
                System.out.print("Choose: ");
                String input = scanner.nextLine();

                switch (input) {
                    case "1" -> {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        userService.register(username, password);
                    }
                    case "2" -> {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        if (userService.login(username, password)) {
                            System.out.println("Login successful.");
                        } else {
                            System.out.println("Invalid credentials.");
                        }
                    }
                    case "0" -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }

            while (userService.getCurrentUser() != null && running) {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. Book a Room");
                System.out.println("2. View My Profile");
                System.out.println("3. Logout");
                System.out.print("Choose: ");
                String input = scanner.nextLine();

                switch (input) {
                    case "1" -> bookingService.startBookingFlow();
                    case "2" -> userService.printCurrentUserProfile();
                    case "3" -> {
                        userService.logout();
                        System.out.println("Logged out.");
                    }
                    default -> System.out.println("Invalid option. Try again.");

                }
            }
        }
    }
}
