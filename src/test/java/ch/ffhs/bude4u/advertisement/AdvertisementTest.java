package ch.ffhs.bude4u.advertisement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;
public class AdvertisementTest {

    private ArrayList<String> advertisementImages = new ArrayList<>();

    @Test
    public void testAdvertisementWithUUIDConstructor() {
        Advertisement advertisement = new Advertisement( "Test Title", "Test Description", "01.01.2023", "Category", "Status", 100000, 3.5, 150, UUID.randomUUID(), "Strasse 1", "Stadt 2", 1234, advertisementImages);

        assertEquals("Test Title", advertisement.getAdvertisementTitle());
        assertEquals("Test Description", advertisement.getMainDescription());
        assertEquals("01.01.2023", advertisement.getCreationDate());
        assertEquals(100000, advertisement.getBuyPrice());
        assertEquals(3.5, advertisement.getNumberRooms());
        assertEquals(150, advertisement.getLivingSpace());
        assertEquals("Category", advertisement.getAdvCategory());
        assertEquals("Status", advertisement.getAdvStatus());
        assertTrue(advertisement.getAdvertisementImages().contains("https://example.com"));
    }

    @Test
    public void testAdvertisementWithDefaultConstructor() {
        Advertisement advertisement = new Advertisement("Test Title", "Test Description", "Category", 100000, 3.5, 150, UUID.randomUUID(), "Strasse 1", "Stadt 2", 1234, advertisementImages);

        assertEquals("Test Title", advertisement.getAdvertisementTitle());
        assertEquals("Test Description", advertisement.getMainDescription());
        assertNotNull(advertisement.getCreationDate());
        assertEquals(100000, advertisement.getBuyPrice());
        assertEquals(3.5, advertisement.getNumberRooms());
        assertEquals(150, advertisement.getLivingSpace());
        assertEquals("Category", advertisement.getAdvCategory());
        assertEquals("offen", advertisement.getAdvStatus());
        assertTrue(advertisement.getAdvertisementImages().contains("https://example.com"));
    }

    @Test
    public void testGetMainImage() {
        Advertisement advertisement = new Advertisement("Test Title", "Test Description", "Category", 100000, 3.5, 150, UUID.randomUUID(), "Strasse 1", "Stadt 2", 1234 , advertisementImages);
        assertEquals("https://example.com", advertisement.getMainImage());

        Advertisement advertisementWithoutImage = new Advertisement("Test Title", "Test Description", "Category", 100000, 3.5, 150, UUID.randomUUID(), "Strasse 1", "Stadt 2", 1234 , advertisementImages);
        assertEquals("", advertisementWithoutImage.getMainImage());
    }
}
