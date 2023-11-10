package ch.ffhs.bude4u.advertisement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AdvertisementServiceTest {

    private AdvertisementService advertisementService;

    @BeforeEach
    public void setup() {
        advertisementService = new AdvertisementService();
    }

    private ArrayList<String> advertisementImages = new ArrayList<>();

    @Test
    public void testGetAdvertisementById() {
        UUID id = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        Optional<Advertisement> advertisement = advertisementService.getAdvertisement(id);
        assertTrue(advertisement.isPresent());
        assertEquals("Haus 1", advertisement.get().getAdvertisementTitle());
    }

    @Test
    public void testGetAdvertisementByStringId() {
        String idString = "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454";
        Optional<Advertisement> advertisement = advertisementService.getAdvertisement(idString);
        assertTrue(advertisement.isPresent());
        assertEquals("Haus 1", advertisement.get().getAdvertisementTitle());
    }

    @Test
    public void testGetAdvertisementByNonExistentId() {
        UUID nonExistentId = UUID.randomUUID();
        Optional<Advertisement> advertisement = advertisementService.getAdvertisement(nonExistentId);
        assertFalse(advertisement.isPresent());
    }

    @Test
    public void testGetAllAdvertisements() {
        Optional<List<Advertisement>> allAdvertisements = advertisementService.getAllAdvertisements();
        assertNotNull(allAdvertisements);
        assertFalse(allAdvertisements.isEmpty());
    }

    @Test
    public void testCreateAdvertisement() {
        Advertisement newAdvertisement = new Advertisement("New House", "Description", "01.01.2023", "Haus", "offen", 100000, 5.5, 142, UUID.randomUUID(), "Strasse 1", "Stadt 2", 1234,  advertisementImages);
        advertisementService.createAdvertisement(newAdvertisement);
        Optional<List<Advertisement>> allAdvertisements = advertisementService.getAllAdvertisements();
        assertTrue(allAdvertisements.stream().anyMatch(x -> x.contains(newAdvertisement)));
    }

    @Test
    public void testUpdateAdvertisement() {
        UUID id = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455");
        Advertisement updatedAdvertisement = new Advertisement("Updated House", "Updated Description", "01.01.2023", "Haus", "offen", 100000, 5.5, 142, UUID.randomUUID(), "Strasse 1", "Stadt 2", 1234, advertisementImages );
        advertisementService.updateAdvertisement(updatedAdvertisement);
        Optional<Advertisement> advertisement = advertisementService.getAdvertisement(id);
        assertTrue(advertisement.isPresent());
        assertEquals("Updated House", advertisement.get().getAdvertisementTitle());
    }

    @Test
    public void testDeleteAdvertisement() {
        UUID id = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456");
        advertisementService.delete(id);
        Optional<Advertisement> deletedAdvertisement = advertisementService.getAdvertisement(id);
        assertFalse(deletedAdvertisement.isPresent());
    }

    @Test
    public void testGetAdvertisementsFromRange() {
        int startIndex = 0;
        int length = 3;
        List<Advertisement> advertisements = advertisementService.getAdvertisementsFromRange(startIndex, length);
        assertNotNull(advertisements);
        assertEquals(length, advertisements.size());
    }
}