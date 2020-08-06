package bluerbn.services;

import bluerbn.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckInService {

    private final CheckInRepository checkInDate;
    private final CacheService cacheService;


    public boolean isCheckInAvailable(int destination, String baggage) {

        String key = getKey(destination, baggage);
        Optional<Object> value = cacheService.get(key);
        if (value.isPresent()) {
            return (boolean)value.get();
        }
        boolean checkingAvailable = checkInDate.isCheckingAvailable(destination, baggage);
        cacheService.add(getKey(destination, baggage), checkingAvailable);
        return checkingAvailable;
    }

    private String getKey(int destination, String baggage) {
        return String.format("CHECK_IN;%s;%s", destination, baggage);
    }

}
