package command;

import service.UserService;

public class ViewProfileCommand implements Command {
    private final UserService userService;

    public ViewProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        userService.printCurrentUserProfile();
    }
}
