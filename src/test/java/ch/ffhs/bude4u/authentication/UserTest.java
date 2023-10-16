package ch.ffhs.bude4u.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(user.getId());
        assertNull(user.getUserRole());
        assertNull(user.getFirstName());
        assertNull(user.getLastName());
    }

    @Test
    public void testSetterAndGetters() {
        user.setId("123");
        user.setUserRole(Userroles.ADMIN);
        user.setFirstName("John");
        user.setLastName("Doe");

        assertEquals("123", user.getId());
        assertEquals(Userroles.ADMIN, user.getUserRole());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }
}
