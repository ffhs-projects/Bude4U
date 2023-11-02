package ch.ffhs.bude4u.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

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
        assertNull(user.getUsername());
    }

    @Test
    public void testSetterAndGetters() {
        UUID newId = UUID.randomUUID();
        user.setId(newId);
        user.setUsername("John Doe");

        assertEquals(newId, user.getId());
        assertEquals("John Doe", user.getUsername());
    }
}
