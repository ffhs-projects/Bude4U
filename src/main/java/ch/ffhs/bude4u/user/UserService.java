package ch.ffhs.bude4u.user;

import ch.ffhs.bude4u.utils.GenericDAO;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final GenericDAO<User> userDao;

    public UserService() {
        userDao = new UserDAO();
    }

    public Optional<User> getUserById(String userId) {
        return userDao.get(userId);
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public void delete(String userId) {
        userDao.delete(userId);
    }

    public void createUser(User user) {
        userDao.create(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public List<User> getUsersFromRange(int startIndex, int length) {
        return userDao.getPaginatedItems(startIndex, length);
    }
}
