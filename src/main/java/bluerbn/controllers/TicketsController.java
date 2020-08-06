package bluerbn.controllers;

import bluerbn.services.TicketsService;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/flights/tickets")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TicketsController {

    private final TicketsService ticketsService;

    @GetMapping("/{id}/available")
    public boolean isAvailableTicket(@PathVariable("id") int ticketId) {

        Preconditions.checkArgument( ticketId >0 ,"ticket id must be greater than 0");
        return ticketsService.isTicketAvailable(ticketId);
    }
}
