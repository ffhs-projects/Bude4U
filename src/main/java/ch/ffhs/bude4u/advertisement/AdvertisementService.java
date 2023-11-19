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
public class AdvertisementService implements Serializable{

    private final GenericDAO<Advertisement> advertisementDao;

    public AdvertisementService() {
        // Inject advertisementDAO or Stub...
        advertisementDao = new AdvertisementDAO();
        // advertisementDao = new AdvertisementStub();
    }

    public Optional<Advertisement> getAdvertisement(UUID advId) {
        return advertisementDao.get(advId);
    }

    public Optional<Advertisement> getAdvertisement(String advId) {
        UUID uuid = UUID.fromString(advId);
        return advertisementDao.get(uuid);
    }

    public Optional<List<Advertisement>> getAllAdvertisements() {
        return advertisementDao.getAll();
    }

    public Optional<List<Advertisement>> getAdvertisementsByUserId(UUID advUserId) {
        return advertisementDao.getByUserId(advUserId);
    }

    public Optional<List<Advertisement>> getAdvertisementByFilter(Long priceFrom, Long priceTo, Double roomFrom, Double roomTo, String category, String city) {

        Optional<List<Advertisement>> advertisementList = advertisementDao.getByFilter(priceFrom, priceTo, roomFrom, roomTo, category, city);
        if (advertisementList.isEmpty()) {
            Advertisement adv = new Advertisement();
            adv.setAdvertiserId(UUID.randomUUID());
            adv.setCategory("test");
            adv.setCity("test");
            adv.setPrice(1.0);
            adv.setRooms(1.0);
            adv.setStreet("test");
            adv.setTitle("test");
            adv.setPostalCode(1);
            advertisementDao.create(adv);
        }

        return advertisementList;
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

    public List<Advertisement> getAdvertisementsFromRange(int pageNumber, int pageSize) {
        return advertisementDao.getPaginatedItems(pageNumber, pageSize);
    }
}
