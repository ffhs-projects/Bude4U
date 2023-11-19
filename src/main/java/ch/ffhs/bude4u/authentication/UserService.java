package ch.ffhs.bude4u.authentication;

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

    private final UserDAO userDao;

    public UserService() {
        userDao = new UserDAO();
    }

    public Optional<User> getUserById(UUID userId) {
        Optional<User> luser = userDao.get(userId);
        return luser;
    }

    public Optional<List<User>> getAllUsers() {
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

    public Optional<User> getUserByName(String username) {
        return userDao.getUserByName(username);
    }
}
