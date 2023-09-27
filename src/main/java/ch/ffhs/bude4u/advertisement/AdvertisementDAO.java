package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;

import java.util.List;
import java.util.Optional;

public class AdvertisementDAO implements GenericDAO<Advertisement> {

    // TODO: Add DB-Logic here (in each method)...

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

    }

    @Override
    public void update(Advertisement advertisement) {

    }

    @Override
    public void delete(String advertisementId) {

    }
}
