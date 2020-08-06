import bluerbn.repositories.CheckInRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CheckInRepositoryTest {


    @InjectMocks
    private CheckInRepository checkInRepository;

    @Test
    public void checkIn() {

        Assert.assertEquals(checkInRepository.isCheckingAvailable(1, "b1"), true);
        Assert.assertEquals(checkInRepository.isCheckingAvailable(3, "b3"), false);
    }


}
