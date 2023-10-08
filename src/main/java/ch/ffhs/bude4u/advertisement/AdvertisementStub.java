package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AdvertisementStub implements GenericDAO<Advertisement> {

    // TODO: Add mock-data into list...
    private final List<Advertisement> mockData;

    public AdvertisementStub() {
        mockData = CreateMockData();
    }

    @Override
    public Optional<Advertisement> get(UUID id) {
        return mockData.stream().filter(adv -> adv.id.equals(id)).findFirst();
    }

    @Override
    public List<Advertisement> getAll() {
        return mockData;
    }

    @Override
    public void create(Advertisement advertisement) {
        mockData.add(advertisement);
    }

    @Override
    public void update(Advertisement advertisement) {
        var successfulRemoval = mockData.removeIf(adv -> adv.id.equals(advertisement.id));
        if (successfulRemoval) mockData.add(advertisement);
    }

    @Override
    public void delete(UUID advertisementId) {
        mockData.removeIf(adv -> adv.id.equals(advertisementId));
    }

    @Override
    public List<Advertisement> getPaginatedItems(int start, int length) {
        start = Math.max(start, 0);
        length = Math.max(length, 1);
        if (mockData.size() < start + length) return mockData.subList(start, mockData.size());
        return mockData.subList(start, start + length);
    }

    private List<Advertisement> CreateMockData() {
        List<Advertisement> mockAdvertisements = new ArrayList<Advertisement>();
        mockAdvertisements.add(new Advertisement(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454"), "Haus 1", "Schön hier 1...", "01.01.2023", "Haus", "offen", 1000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg"));
        mockAdvertisements.add(new Advertisement(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455"), "Haus 2", "Schön hier 2...", "01.01.2023", "Chalet", "offen", 2003, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg"));
        mockAdvertisements.add(new Advertisement(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456"), "Haus 3", "Schön hier 3...", "01.01.2023", "Haus", "offen", 2002, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg"));
        mockAdvertisements.add(new Advertisement(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3457"), "Haus 4", "Schön hier 4...", "01.01.2023", "Wohnung", "offen", 2001, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg"));
        mockAdvertisements.add(new Advertisement(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3458"), "Haus 5", "Schön hier 5...", "01.01.2023", "Haus", "offen", 2000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg"));
        return mockAdvertisements;
    }
}
