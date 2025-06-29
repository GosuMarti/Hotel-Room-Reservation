package service;

import io.UserRepository;
import model.Booking;
import model.User;
import java.util.Map;

import static utils.DateFormater.DISPLAY_FORMAT;

public class UserService {
    private final Map<String, User> users;
    private User currentUser = null;

    public UserService() {
        this.users = UserRepository.loadUsers();
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return false;
        }

        User newUser = new User(username, password);
        users.put(username, newUser);
        saveUsersToFile();
        return true;
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

    public void saveUsersToFile() {
        UserRepository.saveUsers(users);
    }

    public void printCurrentUserProfile() {
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        System.out.println("Username: " + currentUser.getUsername());
        System.out.println("Booking History:");
        for (Booking b : currentUser.getBookingHistory()) {
            System.out.printf("- %s | Room: %s | %s to %s | Total: %.2f\n",
                    b.getId(),
                    b.getRoomNumber(),
                    b.getCheckIn().format(DISPLAY_FORMAT),
                    b.getCheckOut().format(DISPLAY_FORMAT),
                    b.getTotalPrice());
        }
    }
}
