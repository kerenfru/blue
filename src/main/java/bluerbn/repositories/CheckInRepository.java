package bluerbn.repositories;


import bluerbn.exceptions.CheckInNotFoundException;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
@Slf4j
public class CheckInRepository {

    // assuming each destination can have multiple baggages options.
    Map<String, Boolean> checkInData =
        ImmutableMap.of("1;b1", true, "2;b2", true,"2;b1", true,"3;b3", false,"4;b3", true);


    public boolean isCheckingAvailable(int destination, String baggage) {

        if (this.isCheckingExists(destination, baggage)) {
            String checkInId = getCheckInData(destination, baggage);
            log.info(String.format("destination %s and baggage %s exists", destination, baggage));
            return checkInData.get(checkInId);
        }

        log.info(String.format("destination %s and baggage %s doesn't exists", destination, baggage));
        return false;
    }

    public boolean isCheckingExists(int destination, String baggage) {

        boolean isExist = checkInData.containsKey(getCheckInData(destination, baggage));
        if (!isExist) {
            throw new CheckInNotFoundException(destination, baggage);
        }
        return true;
    }

    private String getCheckInData(int destination, String baggage) {
        return String.format("%s;%s", destination, baggage);
    }

}
