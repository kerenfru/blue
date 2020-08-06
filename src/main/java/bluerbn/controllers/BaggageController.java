package bluerbn.controllers;

import bluerbn.services.CheckInService;
import bluerbn.services.TicketsService;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/flights/checkin/")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BaggageController {

    private final CheckInService checkInService;

    @GetMapping("/destination/{destinationId}/baggage/{baggageId}")
    public boolean checkin(@PathVariable("destinationId") int destinationId, @PathVariable("baggageId") String baggageId) {

        Preconditions.checkArgument( destinationId >0 ," destinationId id must be greater than 0");
        return checkInService.isCheckInAvailable(destinationId, baggageId);
    }
}
