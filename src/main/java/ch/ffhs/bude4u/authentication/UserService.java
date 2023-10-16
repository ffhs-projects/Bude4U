package ch.ffhs.bude4u.authentication;

import ch.ffhs.bude4u.utils.GenericDAO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class UserService {

    GenericDAO userDao;

    public UserService() {
        userDao = new UserDAO();
    }

    public Optional<User> getUserById(UUID userId) {
        return userDao.get(userId);
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public void delete(UUID userId) {
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
