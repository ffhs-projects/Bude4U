package ch.ffhs.bude4u.authentication;

import ch.ffhs.bude4u.utils.PBKDF2Hash;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
public class User implements Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    @Setter
    private UUID userId;

    @Column(name = "username")
    @Setter
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    public User() {
    }

    public User(String firstname, String lastname, String username, String password) {
        this.userId = UUID.randomUUID();
        this.firstName = firstname;
        this.lastName = lastname;
        this.username = username;
        this.password = password;
    }

    public User(UUID id, String firstname, String lastname, String username, String password) {
        this.userId = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.username = username;
        this.setPassword(password);
    }


    public void setPassword(String password) {
        this.password = PBKDF2Hash.CreateHash(password);
        //this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}