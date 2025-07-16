package command;

import service.UserService;

import java.util.Scanner;

public class RegisterCommand implements Command {
    private final UserService userService;
    private final Scanner scanner;

    public RegisterCommand(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        userService.register(username, password);
    }
}
