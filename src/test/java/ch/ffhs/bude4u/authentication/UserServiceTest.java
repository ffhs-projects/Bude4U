package ch.ffhs.bude4u.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ch.ffhs.bude4u.utils.GenericDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    private UserService userService;
    private GenericDAO userDao;

    @BeforeEach
    public void setUp() {
        userDao = mock(UserDAO.class);
        userService = new UserService(true);
    }

    @AfterAll
    public void cleanup() {
        userDao = null;
        userService = null;
    }

    @Test
    public void testGetUserById() {
        UUID userId = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");

        Optional<User> retrievedUser = userService.getUserById(userId);

        assertTrue(retrievedUser.isPresent());
    }

    @Test
    public void testGetUserByIdNotFound() {
        UUID userId = UUID.randomUUID();

        Optional<User> retrievedUser = userService.getUserById(userId);

        assertFalse(retrievedUser.isPresent());
    }

    @Test
    public void testGetAllUsers() {
        Optional<List<User>> userList = Optional.of(new ArrayList<>());
        when(userDao.getAll()).thenReturn(userList);

        Optional<List<User>> retrievedUsers = userService.getAllUsers();

        assertEquals(4, retrievedUsers.get().size());
    }

    @Test
    public void testDeleteUser() {
        UUID userId = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");

        userService.delete(userId);

        Optional<List<User>> retrievedUsers = userService.getAllUsers();
        assertEquals(3, retrievedUsers.get().size());

    }

    @Test
    public void testCreateUser() {
        User user = new User();

        userService.createUser(user);


        Optional<List<User>> retrievedUsers = userService.getAllUsers();
        assertEquals(5, retrievedUsers.get().size());
    }

    @Test
    public void testUpdateUser() {
        Optional<User> usr = userService.getUserById(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454"));
        if (usr.isPresent()) {
            usr.get().setLastname("NoDoe");
        }
        userService.updateUser(usr.get());

        Optional<User> updatedUsr = userService.getUserById(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454"));
        assertEquals("NoDoe", updatedUsr.get().getLastname());
    }

    @Test
    public void testGetUsersFromRange() {
        int startIndex = 0;
        int length = 10;
        when(userDao.getPaginatedItems(startIndex, length));

        List<User> retrievedUsers = userService.getUsersFromRange(startIndex, length);

        assertEquals(4, retrievedUsers.size());
    }
}
