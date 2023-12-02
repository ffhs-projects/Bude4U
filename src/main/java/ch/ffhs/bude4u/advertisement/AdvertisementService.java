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

    /**
     * Constructor
     */
    public AdvertisementService() {
        // Inject advertisementDAO or Stub...
        advertisementDao = new AdvertisementDAO();
        // advertisementDao = new AdvertisementStub();
    }

    /**
     * Get advertisement by id
     *
     * @param advId Advertisement id
     * @return Advertisement
     */
    public Optional<Advertisement> getAdvertisement(UUID advId) {
        return advertisementDao.get(advId);
    }

    /**
     * Get advertisement by id
     *
     * @param advId Advertisement id
     * @return Advertisement
     */
    public Optional<Advertisement> getAdvertisement(String advId) {
        UUID uuid = UUID.fromString(advId);
        return advertisementDao.get(uuid);
    }

    /**
     * Get all advertisements
     *
     * @return List of all advertisements
     */
    public Optional<List<Advertisement>> getAllAdvertisements() {
        return advertisementDao.getAll();
    }

    /**
     * Get advertisements by user id
     *
     * @param advUserId User id
     * @return List of advertisements
     */
    public Optional<List<Advertisement>> getAdvertisementsByUserId(UUID advUserId) {
        return advertisementDao.getByUserId(advUserId);
    }

    /**
     * Get advertisements by filter
     *
     * @param priceFrom Price from
     * @param priceTo   Price to
     * @param roomFrom  Room from
     * @param roomTo    Room to
     * @param category  Category
     * @param city      City
     * @return List of advertisements
     */
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

    /**
     * Delete advertisement by id
     *
     * @param advertisementId Advertisement id
     */
    public void delete(UUID advertisementId) {
        advertisementDao.delete(advertisementId);
    }

    /**
     * Create new advertisement
     *
     * @param advertisement Advertisement
     */
    public void createAdvertisement(Advertisement advertisement) {
        advertisementDao.create(advertisement);
    }

    /**
     * Update advertisement
     * @param advertisement Advertisement
     */
    public void updateAdvertisement(Advertisement advertisement) {
        advertisementDao.update(advertisement);
    }

    /**
     * Get advertisements from range
     * @param pageNumber Page number
     * @param pageSize Page size
     * @return List of advertisements
     */
    public List<Advertisement> getAdvertisementsFromRange(int pageNumber, int pageSize) {
        return advertisementDao.getPaginatedItems(pageNumber, pageSize);
    }
}
