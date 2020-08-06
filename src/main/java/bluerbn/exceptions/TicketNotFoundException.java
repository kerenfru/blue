package bluerbn.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TicketNotFoundException extends RuntimeException {
    private int ticketId;

    public TicketNotFoundException(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String getMessage() {
        return String.format("Ticket id %s was not found", ticketId);
    }
}
