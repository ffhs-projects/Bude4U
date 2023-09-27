package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;

import java.util.List;
import java.util.Optional;

public class AdvertisementStub implements GenericDAO<Advertisement> {

    // TODO: Add Stub-logic here (in each method);

    // TODO: Add mock-data here...
    private List<Advertisement> mockData;

    @Override
    public Optional<Advertisement> get(String id) {
        return mockData.stream().filter(adv -> adv.id.equals(id)).findAny();
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
}
