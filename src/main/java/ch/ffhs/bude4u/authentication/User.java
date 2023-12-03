package ch.ffhs.bude4u.authentication;

import ch.ffhs.bude4u.utils.PBKDF2Hash;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Setter
    @Getter
    @Id
    @GeneratedValue
    private UUID id;

    @Getter
    @Setter
    @Column(name = "username")
    private String username;

    @Getter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "firstname")
    private String firstname;

    @Getter
    @Setter
    @Column(name = "lastname")
    private String lastname;

    @Setter
    @Column(name = "theme")
    private String selectedTheme;

    /**
     * Constructor
     */
    public User() {
    }

    /**
     * Constructor
     *
     * @param first    String
     * @param last     String
     * @param username String
     * @param password String
     * @param theme    String
     */
    public User(String first, String last, String username, String password, String theme) {
        this.firstname = first;
        this.lastname = last;
        this.username = username;
        this.setPassword(password);
        this.selectedTheme = theme;
    }

    /**
     * Constructor
     *
     * @param first    String
     * @param last     String
     * @param username String
     * @param password String
     * @param theme    String
     */
    public User(String id, String first, String last, String username, String password, String theme) {
        this.id = UUID.fromString(id);
        this.firstname = first;
        this.lastname = last;
        this.username = username;
        this.setPassword(password);
        this.selectedTheme = theme;
    }

    /**
     * Constructor
     *
     * @param first    String
     * @param last     String
     * @param username String
     * @param password String
     */
    public User(String first, String last, String username, String password) {
        this(first, last, username, password, "saga");
    }

    /**
     * Sets the password
     *
     * @param password String
     */
    public void setPassword(String password) {
        this.password = PBKDF2Hash.CreateHash(password);
    }

    /**
     * Gets the selected theme
     *
     * @return String
     */
    public String getSelectedTheme() {
        return Objects.requireNonNullElse(this.selectedTheme, "saga");
    }

    /**
     * Checks if the password is correct
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    /**
     * Hashcode
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

}