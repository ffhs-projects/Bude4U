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
    private ArrayList<String> advertisementImages = new ArrayList<>();

    /**
     * Constructor
     */
    public AdvertisementStub() {
        mockData = CreateMockData();
    }

    /**
     * Gets an advertisement by id
     *
     * @param id Advertisement id
     * @return Advertisement
     */
    @Override
    public Optional<Advertisement> get(UUID id) {
        return mockData.stream().filter(adv -> adv.getId().equals(id)).findFirst();
    }

    /**
     * Gets all advertisements
     *
     * @return List of advertisements
     */
    @Override
    public Optional<List<Advertisement>> getAll() {
        return Optional.of(mockData);
    }

    /**
     * Creates an advertisement
     *
     * @param advertisement Advertisement
     */
    @Override
    public void create(Advertisement advertisement) {
        mockData.add(advertisement);
    }

    /**
     * Updates an advertisement
     *
     * @param advertisement Advertisement
     */
    @Override
    public void update(Advertisement advertisement) {
        var successfulRemoval = mockData.removeIf(adv -> adv.getId().equals(advertisement.getId()));
        if (successfulRemoval) mockData.add(advertisement);
    }

    /**
     * Deletes an advertisement by id
     *
     * @param advertisementId Advertisement id
     */
    @Override
    public void delete(UUID advertisementId) {
        mockData.removeIf(adv -> adv.getId().equals(advertisementId));
    }

    /**
     * Gets a list of advertisements by page number and page size
     *
     * @param pageNumber Page number
     * @param pageSize   Page size
     * @return List of advertisements
     */
    @Override
    public List<Advertisement> getPaginatedItems(int pageNumber, int pageSize) {
        pageNumber = Math.max(pageNumber, 0);
        pageSize = Math.max(pageSize, 1);
        if (mockData.size() < pageNumber + pageSize) return mockData.subList(pageNumber, mockData.size());
        return mockData.subList(pageNumber, pageNumber + pageSize);
    }

    /**
     * Gets all advertisements by user id
     *
     * @param advUserId User id
     * @return List of advertisements
     */
    @Override
    public Optional<List<Advertisement>> getByUserId(UUID advUserId) {
        return Optional.empty();
    }

    /**
     * Gets all advertisements by filter
     *
     * @param priceFrom Minimum price
     * @param priceTo   Maximum price
     * @param roomFrom  Minimum rooms
     * @param roomTo    Maximum rooms
     * @param category  Category
     * @param city      City
     * @return List of advertisements
     */
    @Override
    public Optional<List<Advertisement>> getByFilter(Long priceFrom, Long priceTo, Double roomFrom, Double roomTo, String category, String city) {
        return Optional.empty();
    }

    /**
     * Creates mock data for testing purposes
     *
     * @return List of mock advertisements
     */
    private List<Advertisement> CreateMockData() {
        advertisementImages.add("https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg");

        List<Advertisement> mockAdvertisements = new ArrayList<>();
        mockAdvertisements.add(new Advertisement("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454", "Haus 1", "Schön hier 1...", "01.01.2023", "Haus", "offen", 100000, 5.5, 142, UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454"), "Strasse 1", "Stadt 1", 1234, advertisementImages));
        mockAdvertisements.add(new Advertisement("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455", "Haus 2", "Schön hier 2...", "01.02.2023", "Chalet", "reserviert", 240000, 5.5, 142, UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455"), "Strasse 2", "Stadt 2", 1234, advertisementImages));
        mockAdvertisements.add(new Advertisement("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456", "Haus 3", "Schön hier 3...", "01.03.2023", "Haus", "offen", 585000, 5.5, 142, UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456"), "Strasse 3", "Stadt 3", 1234, advertisementImages));
        mockAdvertisements.add(new Advertisement("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3457", "Haus 4", "Schön hier 4...", "01.04.2023", "Wohnung", "offen", 1200000, 5.5, 142, UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3457"), "Strasse 4", "Stadt 4", 1234, advertisementImages));
        mockAdvertisements.add(new Advertisement("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3458", "Haus 5", "Schön hier 5...", "01.05.2023", "Haus", "reserviert", 30000, 5.5, 142, UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3458"), "Strasse 5", "Stadt 5", 1234, advertisementImages));
        return mockAdvertisements;
    }
}
