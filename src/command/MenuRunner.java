package command;

import service.UserService;

import java.util.Scanner;

public class MenuRunner {
    private final Scanner scanner;
    private final UserService userService;

    public MenuRunner(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public void runLoginMenu(CommandRegistry registry) {
        while (userService.getCurrentUser() == null) {
            System.out.println("\n=== Welcome to the Hotel System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String input = scanner.nextLine();
            if ("0".equals(input)) {
                System.out.println("Goodbye!");
                System.exit(0);
            }

            registry.get(input).execute();
        }
    }

    public void runMainMenu(CommandRegistry registry) {
        while (userService.getCurrentUser() != null) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Book a Room");
            System.out.println("2. View My Profile");
            System.out.println("3. Logout");
            System.out.print("Choose: ");

            String input = scanner.nextLine();
            registry.get(input).execute();
        }
    }
}
