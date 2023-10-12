package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Getter;

@Named
@SessionScoped
@Getter
public class AdvertisementService implements Serializable {

    private final GenericDAO<Advertisement> advertisementDao;

    public AdvertisementService() {
        // Inject advertisementDAO or Stub...
        //advertisementDao = new AdvertisementDAO();
        advertisementDao = new AdvertisementStub();
    }

    public Optional<Advertisement> getAdvertisement(UUID advId) {
        return advertisementDao.get(advId);
    }

    public Optional<Advertisement> getAdvertisement(String advId) {
        UUID uuid = UUID.fromString(advId);
        return advertisementDao.get(uuid);
    }

    public List<Advertisement> getAllAdvertisements() {
        return advertisementDao.getAll();
    }

    public void delete(UUID advertisementId) {
        advertisementDao.delete(advertisementId);
    }

    public void createAdvertisement(Advertisement advertisement) {
        advertisementDao.create(advertisement);
    }

    public void updateAdvertisement(Advertisement advertisement) {
        advertisementDao.update(advertisement);
    }

    public List<Advertisement> getAdvertisementsFromRange(int startIndex, int length) {
        return advertisementDao.getPaginatedItems(startIndex, length);
    }
}
