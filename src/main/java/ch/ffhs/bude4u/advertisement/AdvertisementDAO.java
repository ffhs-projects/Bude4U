package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class AdvertisementDAO implements GenericDAO<Advertisement> {

    private final EntityManager entityManager;
    private final EntityManagerFactory emf;

    public AdvertisementDAO() {
        this.emf = Persistence.createEntityManagerFactory("default");
        this.entityManager = this.emf.createEntityManager();
    }

    public AdvertisementDAO(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }

    @Override
    public Optional<Advertisement> get(UUID id) {
        return Optional.ofNullable(entityManager.find(Advertisement.class, id));
    }

    @Override
    public Optional<List<Advertisement>> getAll() {
        String jpql = "SELECT adv FROM Advertisement adv";
        Query query = entityManager.createQuery(jpql);

        List<Advertisement> advertisements = query.getResultList();
        if (advertisements.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(advertisements );
        }
    }

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

    public Optional<List<Advertisement>> getByFilter(Long priceFrom, Long priceTo, Double roomFrom, Double roomTo, String category) {


        String jpql = "SELECT adv FROM Advertisement adv WHERE adv.buyPrice >= :priceFrom AND adv.buyPrice <= :priceTo AND adv.numberRooms >= :roomFrom AND adv.numberRooms <= :roomTo AND (adv.advCategory = :category OR :category = 'Any')";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("priceFrom", priceFrom);
        query.setParameter("priceTo", priceTo);
        query.setParameter("roomFrom", roomFrom);
        query.setParameter("roomTo", roomTo);
        query.setParameter("category", category);

        List<Advertisement> advertisements = query.getResultList();
        if (advertisements.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(advertisements);
        }
    }

    @Override
    public void create(Advertisement advertisement) {
        entityManager.getTransaction().begin();
        entityManager.persist(advertisement);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Advertisement advertisement) {
        Optional<Advertisement> advToUpdate = get(advertisement.getId());
        if (advToUpdate.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.merge(advertisement);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void delete(UUID advertisementId) {
        Optional<Advertisement> advToDelete = get(advertisementId);
        if (advToDelete.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(advToDelete.get());
            entityManager.getTransaction().commit();
        }
    }

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
