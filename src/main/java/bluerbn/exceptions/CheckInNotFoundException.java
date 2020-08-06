package bluerbn.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CheckInNotFoundException extends RuntimeException {
    private int destinationId;
    private String baggageId;

    public CheckInNotFoundException(int destinationId, String baggageId) {
        this.destinationId = destinationId;
        this.baggageId = baggageId;
    }

    @Override
    public String getMessage() {
        return String.format("Check in with destination id %s and baggage id %s was not found", destinationId, baggageId);
    }
}
