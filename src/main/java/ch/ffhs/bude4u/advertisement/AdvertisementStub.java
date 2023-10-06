package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdvertisementStub implements GenericDAO<Advertisement> {

    // TODO: Add mock-data into list...
    private final List<Advertisement> mockData;

    public AdvertisementStub() {
        mockData = CreateMockData();
    }

    @Override
    public Optional<Advertisement> get(String id) {
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
    public void delete(String advertisementId) {
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
        mockAdvertisements.add(new Advertisement("1", "Haus 1", "Schön hier 1...", "01.01.2023", "Haus", "offen", 1000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg"));
        mockAdvertisements.add(new Advertisement("2", "Haus 2", "Schön hier 2...", "01.01.2023", "Chalet", "offen", 2003, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg"));
        mockAdvertisements.add(new Advertisement("3", "Haus 3", "Schön hier 3...", "01.01.2023", "Haus", "offen", 2002, 5.5, 142, ""));
        mockAdvertisements.add(new Advertisement("4", "Haus 4", "Schön hier 4...", "01.01.2023", "Wohnung", "offen", 2001, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg"));
        mockAdvertisements.add(new Advertisement("5", "Haus 5", "Schön hier 5...", "01.01.2023", "Haus", "offen", 2000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg"));
        return mockAdvertisements;
    }
}
