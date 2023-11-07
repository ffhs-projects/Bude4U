package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AdvertisementStub implements GenericDAO<Advertisement> {

    private final List<Advertisement> mockData;

    public AdvertisementStub() {
        mockData = CreateMockData();
    }

    @Override
    public Optional<Advertisement> get(UUID id) {
        return mockData.stream().filter(adv -> adv.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<List<Advertisement>> getAll() {
        return Optional.of(mockData);
    }

    @Override
    public void create(Advertisement advertisement) {
        mockData.add(advertisement);
    }

    @Override
    public void update(Advertisement advertisement) {
        var successfulRemoval = mockData.removeIf(adv -> adv.getId().equals(advertisement.getId()));
        if (successfulRemoval) mockData.add(advertisement);
    }

    @Override
    public void delete(UUID advertisementId) {
        mockData.removeIf(adv -> adv.getId().equals(advertisementId));
    }

    @Override
    public List<Advertisement> getPaginatedItems(int pageNumber, int pageSize) {
        pageNumber = Math.max(pageNumber, 0);
        pageSize = Math.max(pageSize, 1);
        if (mockData.size() < pageNumber + pageSize) return mockData.subList(pageNumber, mockData.size());
        return mockData.subList(pageNumber, pageNumber + pageSize);
    }

    @Override
    public Optional<List<Advertisement>> getByUserId(UUID advUserId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Advertisement>> getByFilter(Long priceFrom, Long priceTo, Double roomFrom, Double roomTo, String category) {
        return Optional.empty();
    }

    private List<Advertisement> CreateMockData() {
        List<Advertisement> mockAdvertisements = new ArrayList<>();
        mockAdvertisements.add(new Advertisement("Haus 1", "Schön hier 1...", "01.01.2023", "Haus", "offen", 100000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")));
        mockAdvertisements.add(new Advertisement("Haus 2", "Schön hier 2...", "01.02.2023", "Chalet", "reserviert", 240000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455")));
        mockAdvertisements.add(new Advertisement("Haus 3", "Schön hier 3...", "01.03.2023", "Haus", "offen", 585000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456")));
        mockAdvertisements.add(new Advertisement("Haus 4", "Schön hier 4...", "01.04.2023", "Wohnung", "offen", 1200000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3457")));
        mockAdvertisements.add(new Advertisement("Haus 5", "Schön hier 5...", "01.05.2023", "Haus", "reserviert", 30000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3458")));
        return mockAdvertisements;
    }
}
