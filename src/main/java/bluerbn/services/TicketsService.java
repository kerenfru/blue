package bluerbn.services;

import bluerbn.repositories.TicketsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TicketsService {

    private final TicketsRepository ticketsData;
    private final CacheService cacheService;


    public boolean isTicketAvailable(int ticketId) {

        String key = getKey(ticketId);
        Optional<Object> value = cacheService.get(key);

        if (value.isPresent()){
            return (boolean)value.get();
        }

        boolean ticketAvailable = ticketsData.isTicketAvailable(ticketId);
        cacheService.add(key, ticketAvailable);
        return ticketAvailable;
    }

    private String getKey(int ticketId) {
        return String.format("TICKET;%s", ticketId);
    }

}
