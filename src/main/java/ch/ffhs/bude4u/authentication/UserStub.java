package ch.ffhs.bude4u.authentication;

import ch.ffhs.bude4u.advertisement.Advertisement;
import ch.ffhs.bude4u.utils.GenericDAO;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserStub implements GenericDAO<User> {

    private final List<User> mockData;

    public UserStub() {
        mockData = CreateMockData();
    }

    private List<User> CreateMockData() {
        List<User> data = new ArrayList<>();
        data.add(new User("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454", "John", "Doe", "john@doe", "123", "arya"));
        data.add(new User("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455", "Jane", "Doe", "jane@doe", "123", "arya"));
        data.add(new User("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456", "Mac", "AndCheese", "Mac@caroni", "123", "arya"));
        data.add(new User("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3457", "Knob", "Lee", "knob@lauch", "123", "arya"));
        return data;
    }

    /**
     * Gets an user by id
     *
     * @param id User id
     * @return User
     */
    @Override
    public Optional<User> get(UUID id) {
        return mockData.stream().filter(usr -> usr.getId().equals(id)).findFirst();
    }

    /**
     * Gets an user by name
     *
     * @param name User name
     * @return User
     */
    public Optional<User> getUserByName(String name) {
        return mockData.stream().filter(usr -> usr.getUsername().equals(name)).findFirst();
    }

    /**
     * Gets all users
     *
     * @return List of users
     */
    @Override
    public Optional<List<User>> getAll() {
        return Optional.of(mockData);
    }

    /**
     * Creates a user
     *
     * @param user User
     */
    @Override
    public void create(User user) {
        mockData.add(user);
    }

    /**
     * Updates a user
     *
     * @param user User
     */
    @Override
    public void update(User user) {
        var successfulRemoval = mockData.removeIf(usr -> usr.getId().equals(user.getId()));
        if (successfulRemoval) mockData.add(user);
    }

    /**
     * Deletes an user by id
     *
     * @param id User id
     */
    @Override
    public void delete(UUID id) {
        mockData.removeIf(adv -> adv.getId().equals(id));
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
        if (mockData.size() < pageNumber + pageSize) return mockData.subList(pageNumber, mockData.size());
        return mockData.subList(pageNumber, pageNumber + pageSize);
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
