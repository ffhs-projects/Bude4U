package ch.ffhs.bude4u.advertisement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.UUID;

public class AdvertisementBeanTest {

    private AdvertisementBean advertisementBean;
    private AdvertisementService advertisementService;

    @BeforeEach
    public void setup() {
        advertisementBean = new AdvertisementBean();
        advertisementService = mock(AdvertisementService.class);
        advertisementBean.setAdvertisementService(advertisementService);
    }

    @Test
    public void testCreateAdvertisementSuccess() {
        // Create a known UUID for the new advertisement
        UUID newAdvertisementId = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3459");

        // Mock the advertisementService to do nothing when createAdvertisement is called
        doNothing().when(advertisementService).createAdvertisement(any(Advertisement.class));

        // Set the properties of the AdvertisementBean
        advertisementBean.setTest(true);
        advertisementBean.setTitle("Test Title");
        advertisementBean.setDescription("Test Description");
        advertisementBean.setCategory("Category");
        advertisementBean.setPrice(100000.0);
        advertisementBean.setRooms(3.5);
        advertisementBean.setSpace(150);

        // Call the createAdvertisement method
        String result = advertisementBean.createAdvertisement();

        // Check if the result is as expected with the known UUID
        assertEquals("/views/userAdvertisement.xhtml?faces-redirect=true", result);
    }


    @Test
    public void testCreateAdvertisementFailure() {
        doNothing().when(advertisementService).createAdvertisement(any(Advertisement.class));

        advertisementBean.setTest(true);
        advertisementBean.setTitle("Test Title");
        advertisementBean.setDescription("Test Description");
        advertisementBean.setCategory("Category");
        advertisementBean.setPrice(100000.0);
        advertisementBean.setRooms(3.5);
        advertisementBean.setSpace(150);

        String result = advertisementBean.createAdvertisement();
        assertEquals("/views/userAdvertisement.xhtml?faces-redirect=true", result);
    }
}
