package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
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

    public void createOne() {
        var adv = new Advertisement("Haus 1", "Schön hier 1...", "01.01.2023", "Haus", "offen", 100000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg");
        create(adv);
    }

    @Override
    public Optional<Advertisement> get(UUID id) {
        return Optional.ofNullable(entityManager.find(Advertisement.class, id));
    }

    @Override
    public List<Advertisement> getAll() {
        return entityManager.createQuery("SELECT adv FROM Advertisement adv").getResultList();
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
            entityManager.persist(advertisement);
            // TODO: maybe better solution: check each property that can be overwritten...
            // advToUpdate.setProperty1(advertisement.getProperty1());
            // advToUpdate.setProperty2(advertisement.getProperty2());
            // advToUpdate.setProperty3(advertisement.getProperty3());
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void delete(UUID advertisementId) {
        Optional<Advertisement> advToDelete = get(advertisementId);
        if (advToDelete.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(advToDelete);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<Advertisement> getPaginatedItems(int start, int length) {
        return null;
    }
}
