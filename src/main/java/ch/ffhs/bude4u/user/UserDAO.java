package ch.ffhs.bude4u.user;

import ch.ffhs.bude4u.utils.GenericDAO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class UserDAO implements GenericDAO<User> {
    @Override
    public Optional<User> get(UUID id) {
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
    public void delete(UUID id) {

    }

    @Override
    public List<User> getPaginatedItems(int pageNumber, int pageSize) {
        return null;
    }
}
