package bluerbn.repositories;

import bluerbn.exceptions.TicketNotFoundException;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Slf4j
public class TicketsRepository {
    Map<Integer, Boolean> tickets =
        ImmutableMap.of(1, true, 2, true,3, true,4, false,5, false);


    public boolean isTicketAvailable(int ticketId) {
        if (this.isTicketsExists(ticketId)) {
            log.info(String.format("ticket id %s exists",  ticketId));
            return tickets.get(ticketId);
        }

        log.info(String.format("ticket id %s doesn't exist", ticketId));
        return false;
    }

    public boolean isTicketsExists(int ticketId) {

        boolean isExist = tickets.keySet().contains(ticketId);
        if (!isExist) {
            throw new TicketNotFoundException(ticketId);
        }

        return true;
    }
}
