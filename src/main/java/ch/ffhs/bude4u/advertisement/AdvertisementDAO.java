package ch.ffhs.bude4u.advertisement;

import ch.ffhs.bude4u.utils.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class AdvertisementDAO implements GenericDAO<Advertisement> {

    // TODO: Add DB-Logic here (in each method)...

    @PersistenceContext
    private EntityManager em;
    private EntityManagerFactory emf;

    public AdvertisementDAO() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();

        var adv = new Advertisement(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454"), "Haus 1", "Schön hier 1...", "01.01.2023", "Haus", "offen", 100000, 5.5, 142, "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg");
        em.persist(adv);
    }

    @Override
    public Optional<Advertisement> get(UUID id) {
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
    public void delete(UUID advertisementId) {

    }

    @Override
    public List<Advertisement> getPaginatedItems(int start, int length) {
        return null;
    }
}
