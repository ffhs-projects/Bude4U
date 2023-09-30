package ch.ffhs.bude4u.user;

import ch.ffhs.bude4u.utils.GenericDAO;

import java.util.List;
import java.util.Optional;

public class UserDAO implements GenericDAO<User> {
    @Override
    public Optional<User> get(String id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<User> getPaginatedItems(int start, int length) {
        return null;
    }
}
