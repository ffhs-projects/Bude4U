package ch.ffhs.bude4u.advertisement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

public class AdvertisementTest {

    private ArrayList<String> advertisementImages = new ArrayList<>();

    @Test
    public void testAdvertisementWithUUIDConstructor() {
        Advertisement advertisement = new Advertisement("Test Title", "Test Description", "01.01.2023", "Category", "Status", 100000, 3.5, 150, UUID.randomUUID(), "Strasse 1", "Stadt 2", 1234, advertisementImages);

        assertEquals("Test Title", advertisement.getTitle());
        assertEquals("Test Description", advertisement.getDescription());
        assertEquals("01.01.2023", advertisement.getDate());
        assertEquals(100000, advertisement.getPrice());
        assertEquals(3.5, advertisement.getRooms());
        assertEquals(150, advertisement.getSpace());
        assertEquals("Category", advertisement.getCategory());
        assertEquals("Status", advertisement.getStatus());
    }


    @Test
    public void testAdvertisementWithDefaultConstructor() {
        Advertisement advertisement = new Advertisement("Test Title", "Test Description", "Category", 100000, 3.5, 150, UUID.randomUUID(), "Strasse 1", "Stadt 2", 1234, advertisementImages);

        assertEquals("Test Title", advertisement.getTitle());
        assertEquals("Test Description", advertisement.getDescription());
        assertNotNull(advertisement.getDate());
        assertEquals(100000, advertisement.getPrice());
        assertEquals(3.5, advertisement.getRooms());
        assertEquals(150, advertisement.getSpace());
        assertEquals("Category", advertisement.getCategory());
        assertEquals("offen", advertisement.getStatus());
    }


    @Test
    public void testGetDefaultImage() {
        Advertisement advertisement = new Advertisement("Test Title", "Test Description", "Category", 100000, 3.5, 150, UUID.randomUUID(), "Strasse 1", "Stadt 2", 1234, advertisementImages);
        assertTrue(advertisement.getMainImage().startsWith("data:image/png;base64,/9j/4AA"));
    }


    @Test
    public void testGetMainImage() {
        String imageAsBase64 = "/ewi3430GHEJ%&GERJREGJIOJ";
        ArrayList<String> images = new ArrayList<>();
        images.add(imageAsBase64);
        Advertisement advertisement = new Advertisement("Test Title", "Test Description", "Category", 100000, 3.5, 150, UUID.randomUUID(), "Strasse 1", "Stadt 2", 1234, images);
        assertEquals("data:image/png;base64,/ewi3430GHEJ%&GERJREGJIOJ", advertisement.getMainImage());
    }
}
