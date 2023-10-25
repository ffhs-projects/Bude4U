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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    private UserService userService;
    private GenericDAO userDao;

    @BeforeEach
    public void setUp() {
        userDao = mock(UserDAO.class);
        userService = new UserService();
    }

    @Test
    public void testGetUserById() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setUserId(userId);

        when(userDao.get(userId)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(userId);

        assertTrue(retrievedUser.isPresent());
        assertEquals(userId.toString(), retrievedUser.get().getUserId());
    }

    @Test
    public void testGetUserByIdNotFound() {
        UUID userId = UUID.randomUUID();

        when(userDao.get(userId)).thenReturn(Optional.empty());

        Optional<User> retrievedUser = userService.getUserById(userId);

        assertFalse(retrievedUser.isPresent());
    }

    @Test
    public void testGetAllUsers() {
        Optional<List<User>> userList = Optional.of(new ArrayList<>());
        when(userDao.getAll()).thenReturn(userList);

        Optional<List<User>> retrievedUsers = userService.getAllUsers();

        assertEquals(userList, retrievedUsers);
    }

    @Test
    public void testDeleteUser() {
        UUID userId = UUID.randomUUID();

        userService.delete(userId);

        verify(userDao, times(1)).delete(userId);
    }

    @Test
    public void testCreateUser() {
        User user = new User();

        userService.createUser(user);

        verify(userDao, times(1)).create(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();

        userService.updateUser(user);

        verify(userDao, times(1)).update(user);
    }

    @Test
    public void testGetUsersFromRange() {
        int startIndex = 0;
        int length = 10;
        List<User> userList = new ArrayList<>();
        when(userDao.getPaginatedItems(startIndex, length)).thenReturn(userList);

        List<User> retrievedUsers = userService.getUsersFromRange(startIndex, length);

        assertEquals(userList, retrievedUsers);
    }
}
