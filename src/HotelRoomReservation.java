import command.*;
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

        CommandRegistry authCommands = new CommandRegistry();
        authCommands.register("1", new RegisterCommand(userService, scanner));
        authCommands.register("2", new LoginCommand(userService, scanner));

        CommandRegistry mainMenuCommands = new CommandRegistry();
        mainMenuCommands.register("1", new BookRoomCommand(bookingService));
        mainMenuCommands.register("2", new ViewProfileCommand(userService));
        mainMenuCommands.register("3", new LogoutCommand(userService));

        MenuRunner runner = new MenuRunner(scanner, userService);

        while (true) {
            runner.runLoginMenu(authCommands);
            runner.runMainMenu(mainMenuCommands);
        }
    }
}
