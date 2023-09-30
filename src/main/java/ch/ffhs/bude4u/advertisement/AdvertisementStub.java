package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;
import ch.ffhs.bude4u.utils.PaginationDAO;

import java.util.List;
import java.util.Optional;

public class AdvertisementStub implements GenericDAO<Advertisement>, PaginationDAO<Advertisement> {

    // TODO: Add mock-data into list...
    private List<Advertisement> mockData;

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

    }

    @Override
    public void delete(String advertisementId) {
        mockData.removeIf(adv -> adv.id.equals(advertisementId));
    }

    @Override
    public List<Advertisement> getPaginatedItems(int start, int length) {
        if (mockData.size() < start + length) return mockData.subList(start, mockData.size());
        return mockData.subList(start, start + length);
    }
}
