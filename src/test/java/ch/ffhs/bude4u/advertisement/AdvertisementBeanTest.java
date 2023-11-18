package ch.ffhs.bude4u.advertisement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.primefaces.event.FileUploadEvent;

import static org.mockito.Mockito.*;

import java.util.EventObject;
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
        advertisementBean.setAdvertisementTitle("Test Title");
        advertisementBean.setMainDescription("Test Description");
        advertisementBean.setAdvCategory("Category");
        advertisementBean.setBuyPrice(100000.0);
        advertisementBean.setNumberRooms(3.5);
        advertisementBean.setLivingSpace(150);

        // Call the createAdvertisement method
        String result = advertisementBean.createAdvertisement();

        // Check if the result is as expected with the known UUID
        assertEquals("/views/advertisement.xhtml?advertisement=" + newAdvertisementId + "&faces-redirect=true", result);

        // Verify that createAdvertisement was called once
        verify(advertisementService, times(1)).createAdvertisement(any(Advertisement.class));
    }




    @Test
    public void testCreateAdvertisementFailure() {
        doThrow(new RuntimeException("Failed to create advertisement")).when(advertisementService).createAdvertisement(any(Advertisement.class));

        advertisementBean.setAdvertisementTitle("Test Title");
        advertisementBean.setMainDescription("Test Description");
        advertisementBean.setAdvCategory("Category");
        advertisementBean.setBuyPrice(100000.0);
        advertisementBean.setNumberRooms(3.5);
        advertisementBean.setLivingSpace(150);

        String result = advertisementBean.createAdvertisement();

        assertEquals("/views/advertisementFailed.xhtml", result);
        verify(advertisementService, times(1)).createAdvertisement(any(Advertisement.class));
    }
}
