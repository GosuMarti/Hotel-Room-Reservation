package command;

import service.BookingService;

public class BookRoomCommand implements Command {
    private final BookingService bookingService;

    public BookRoomCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void execute() {
        bookingService.startBookingFlow();
    }
}
