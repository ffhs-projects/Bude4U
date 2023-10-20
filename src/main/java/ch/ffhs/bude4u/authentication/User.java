package ch.ffhs.bude4u.authentication;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="user")
@Getter
public class User implements Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "security_role")
    private String securityRole;

    public void setUserId(UUID userId) {
        this.userId = userId;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setSecurityRole(String securityRole) {
        this.securityRole = securityRole;
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