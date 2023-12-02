package ch.ffhs.bude4u.authentication;

import ch.ffhs.bude4u.utils.GenericDAO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDAO implements GenericDAO<User> {

    private final EntityManager entityManager;
    private final EntityManagerFactory emf;

    /**
     * Constructor
     */
    public UserDAO() {
        this.emf = Persistence.createEntityManagerFactory("default");
        this.entityManager = this.emf.createEntityManager();
    }


    /**
     * Gets an user by id
     *
     * @param id User id
     * @return User
     */
    @Override
    public Optional<User> get(UUID id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    /**
     * Gets an user by name
     *
     * @param name User name
     * @return User
     */
    public Optional<User> getUserByName(String name) {
        try {
            String jpql = "SELECT u FROM User u WHERE u.username = :username";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("username", name);

            User user = (User) query.getSingleResult();
            return Optional.of(user);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    /**
     * Gets all users
     *
     * @return List of users
     */
    @Override
    public Optional<List<User>> getAll() {
        String jpql = "SELECT u FROM User u";
        Query query = entityManager.createQuery(jpql);

        List<User> userList = query.getResultList();
        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList);
        }
    }

    /**
     * Creates an user
     *
     * @param user User
     */
    @Override
    public void create(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Updates an user
     *
     * @param user User
     */
    @Override
    public void update(User user) {
        Optional<User> userToUpdate = get(user.getId());
        if (userToUpdate.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Deletes an user by id
     *
     * @param id User id
     */
    @Override
    public void delete(UUID id) {
        Optional<User> userToDelete = get(id);
        if (userToDelete.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(userToDelete);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Gets the page count
     *
     * @return long
     */
    @Override
    public List<User> getPaginatedItems(int pageNumber, int pageSize) {
        pageNumber = Math.max(pageNumber, 0);
        pageSize = Math.max(pageSize, 1);
        Query query = entityManager.createQuery("SELECT user FROM User user");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    /**
     * Gets all users by user id
     *
     * @param advUserId User id
     * @return List of users
     */
    @Override
    public Optional<List<User>> getByUserId(UUID advUserId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> getByFilter(Long priceFrom, Long priceTo, Double roomFrom, Double roomTo, String category, String city) {
        return Optional.empty();
    }
}

