package ch.ffhs.bude4u.authentication;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.Serializable;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name="user")
@Getter
public class User implements Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    @Setter
    private UUID userId;

    @Column(name = "username")
    @Setter
    private String username;

    @Column(name = "security_role")
    @Setter
    private String securityRole;

    @Column(name = "password")
    private String password;

    public void setPassword(String password) {
        this.password = CreatePBKDF2Hash(password);
        //this.password = password;
    }

    private String CreatePBKDF2Hash(String password) {
        byte[] salt = "12345678".getBytes();

        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Arrays.toString(hash);
        }
        catch (Exception ignored) {
            // TODO: what happens if pw could not be encrypted?
        }
        throw new IllegalArgumentException();
    }

    public boolean CheckPassword(String password) {
        String hashInsertedPassword = CreatePBKDF2Hash(password);
        return hashInsertedPassword.equals(getPassword());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (securityRole != null ? !securityRole.equals(user.securityRole) : user.securityRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (securityRole != null ? securityRole.hashCode() : 0);
        return result;
    }
}