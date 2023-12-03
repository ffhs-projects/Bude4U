package ch.ffhs.bude4u.authentication;

import ch.ffhs.bude4u.utils.GenericDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@SessionScoped
@Named
public class UserService implements Serializable {

    private final GenericDAO userDao;

    /**
     * Constructor
     */
    public UserService() {
        this(false);
    }

    public UserService(boolean testSetUp) {
        if (testSetUp) {
            userDao = new UserStub();
        } else {
            userDao = new UserDAO();
        }
    }

    /**
     * Gets an user by id
     *
     * @param userId User id
     * @return User
     */
    public Optional<User> getUserById(UUID userId) {
        Optional<User> luser = userDao.get(userId);
        return luser;
    }

    /**
     * Gets all users
     *
     * @return List of users
     */
    public Optional<List<User>> getAllUsers() {
        return userDao.getAll();
    }

    /**
     * Deletes an user
     *
     * @param userId User id
     */
    public void delete(UUID userId) {
        userDao.delete(userId);
    }

    /**
     * Creates an user
     *
     * @param user User
     */
    public void createUser(User user) {
        userDao.create(user);
    }

    /**
     * Updates an user
     *
     * @param user User
     */
    public void updateUser(User user) {
        userDao.update(user);
    }

    /**
     * Gets users from range
     *
     * @param startIndex Wherer to start from
     * @param length     How many users to get
     * @return List of users
     */
    public List<User> getUsersFromRange(int startIndex, int length) {
        return userDao.getPaginatedItems(startIndex, length);
    }

    /**
     * Gets user by name
     *
     * @return User by name
     */
    public Optional<User> getUserByName(String username) {
        UserDAO daoCast = (UserDAO) userDao;
        return daoCast.getUserByName(username);
    }
}
