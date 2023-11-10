package ch.ffhs.bude4u.authentication;

import ch.ffhs.bude4u.utils.PBKDF2Hash;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "theme")
    private String theme;

    public User() {
    }

    public User(String first, String last, String username, String password, String theme) {
        this.firstname = first;
        this.lastname = last;
        this.username = username;
        this.setPassword(password);
        this.theme = theme;
    }

    public User(String first, String last, String username, String password) {
        this(first, last, username, password, "saga");
    }


    public void setPassword(String password) {
        this.password = PBKDF2Hash.CreateHash(password);
    }

    public String getTheme() {
        return Objects.requireNonNullElse(this.theme, "saga");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

}