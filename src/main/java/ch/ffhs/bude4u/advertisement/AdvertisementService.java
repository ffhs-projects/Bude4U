package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;

import java.util.List;
import java.util.Optional;

public class AdvertisementService {

    private final GenericDAO<Advertisement> advertisementDao;

    public AdvertisementService() {
        // Inject advertisementDAO or Stub...
        //advertisementDao = new AdvertisementDAO();
        advertisementDao = new AdvertisementStub();
    }

    public Optional<Advertisement> getAdvertisement(String advId) {
        return advertisementDao.get(advId);
    }

    public List<Advertisement> getAllAdvertisements() {
        return advertisementDao.getAll();
    }

    public void deleteAdvertisement(Advertisement advertisement) {
        advertisementDao.delete(advertisement);
    }

    public void createAdvertisement(Advertisement advertisement) {
        advertisementDao.create(advertisement);
    }

    public void updateAdvertisement(Advertisement advertisement) {
        advertisementDao.update(advertisement);
    }
}
