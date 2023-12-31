package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AdvertisementDAO implements GenericDAO<Advertisement> {

    private final EntityManager entityManager;

    /**
     * Constructor
     */
    public AdvertisementDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.entityManager = emf.createEntityManager();
    }

    /**
     * Get advertisement by id
     *
     * @param id Advertisement id
     * @return Advertisement
     */
    @Override
    public Optional<Advertisement> get(UUID id) {
        return Optional.ofNullable(entityManager.find(Advertisement.class, id));
    }

    /**
     * Get all advertisements
     *
     * @return List of all advertisements
     */
    @Override
    public Optional<List<Advertisement>> getAll() {
        String jpql = "SELECT adv FROM Advertisement adv";
        Query query = entityManager.createQuery(jpql);

        List<Advertisement> advertisements = query.getResultList();
        if (advertisements.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(advertisements);
        }
    }

    /**
     * Get all advertisements by user id
     *
     * @param advUserId User id
     * @return List of advertisements
     */
    public Optional<List<Advertisement>> getByUserId(UUID advUserId) {
        String jpql = "SELECT adv FROM Advertisement adv WHERE adv.advertiserId = :advUserId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("advUserId", advUserId);

        List<Advertisement> advertisements = query.getResultList();
        if (advertisements.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(advertisements);
        }
    }


    /**
     * Get all advertisements by filter
     *
     * @param priceFrom Price from
     * @param priceTo   Price to
     * @param roomFrom  Room from
     * @param roomTo    Room to
     * @param category  Category
     * @param city      City
     * @return List of advertisements
     */
    public Optional<List<Advertisement>> getByFilter(Long priceFrom, Long priceTo, Double roomFrom, Double roomTo, String category, String city) {

        String jpql = "SELECT adv FROM Advertisement adv WHERE adv.price >= :priceFrom AND adv.price <= :priceTo AND adv.rooms >= :roomFrom AND adv.rooms <= :roomTo AND (adv.category = :category OR :category = 'Any' ) AND (adv.city = :city OR :city = 'Any')";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("priceFrom", priceFrom);
        query.setParameter("priceTo", priceTo);
        query.setParameter("roomFrom", roomFrom);
        query.setParameter("roomTo", roomTo);
        query.setParameter("category", category);
        query.setParameter("city", city);

        List<Advertisement> advertisements = query.getResultList();
        if (advertisements.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(advertisements);
        }
    }

    /**
     * Create new advertisement
     *
     * @param advertisement Advertisement
     */
    @Override
    public void create(Advertisement advertisement) {
        entityManager.getTransaction().begin();
        entityManager.persist(advertisement);
        entityManager.getTransaction().commit();
    }

    /**
     * Update advertisement
     *
     * @param advertisement Advertisement
     */
    @Override
    public void update(Advertisement advertisement) {
        Optional<Advertisement> advToUpdate = get(advertisement.getId());
        if (advToUpdate.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.merge(advertisement);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Delete advertisement
     *
     * @param advertisementId Advertisement id
     */
    @Override
    public void delete(UUID advertisementId) {
        Optional<Advertisement> advToDelete = get(advertisementId);
        if (advToDelete.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(advToDelete.get());
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Get paginated items
     *
     * @param pageNumber Page number
     * @param pageSize   Page size
     * @return List of advertisements
     */
    @Override
    public List<Advertisement> getPaginatedItems(int pageNumber, int pageSize) {
        pageNumber = Math.max(pageNumber, 0);
        pageSize = Math.max(pageSize, 1);
        Query query = entityManager.createQuery("SELECT adv FROM Advertisement adv");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }
}
