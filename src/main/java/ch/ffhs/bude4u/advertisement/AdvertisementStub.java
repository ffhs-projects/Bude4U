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
        return Optional.empty();
    }

    @Override
    public List<Advertisement> getAll() {
        return null;
    }

    @Override
    public void create(Advertisement advertisement) {
        mockData.add(advertisement);
    }

    @Override
    public void update(Advertisement advertisement) {

    }

    @Override
    public void delete(Advertisement advertisement) {
        mockData.remove(advertisement);
    }
}
