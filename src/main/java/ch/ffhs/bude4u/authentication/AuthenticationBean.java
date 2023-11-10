package ch.ffhs.bude4u.authentication;

import ch.ffhs.bude4u.utils.PBKDF2Hash;
import jakarta.el.MethodExpression;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@SessionScoped
@Named
@Getter
@Setter
public class AuthenticationBean implements Serializable {

    @Inject
    private UserService userService;

    private HttpSession session = null;

    @Setter(AccessLevel.PRIVATE)
    private boolean authenticated = false;
    private String username = null;
    private String password = null;
    private String firstName = null;
    private String lastName = null;

    @Setter
    private String updateUsername = null;
    private String updatePassword = null;
    private String updateFirstName = null;
    private String updateLastName = null;
    private String updateTheme = null;

    User user;

    public String register() {
        String firstName = getFirstName();
        String lastName = getLastName();
        String userName = getUsername();
        String password = getPassword();

        // Check if user with specific username is not in db.
        Optional<User> user = userService.getUserByName(userName);
        if (user.isPresent()) {
            // This means user is already registered, navigate back to login page.
            return "/views/login.xhtml";
        }
        // Create new user
        try {
            User newUser = new User(firstName, lastName, username, password);
            userService.createUser(newUser);
            // New user created, navigate back to login page.
            return "/views/login.xhtml";
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

    public HttpSession getSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();

        return session;
    }

    public String login() {
        String userNameInput = getUsername();
        String passwordInput = getPassword();
        session = getSession();

        Optional<User> user = userService.getUserByName(userNameInput);

        if (user.isEmpty()) return "/views/loginFailed.xhtml";

        String hashedPw = user.get().getPassword();
        boolean pwMatch = PBKDF2Hash.CheckPassword(hashedPw, passwordInput);

        if (pwMatch) {
            session.setAttribute("authenticated", true);
            session.setAttribute("username", userNameInput);
            session.setAttribute("userId", user.get().getId());
            this.authenticated = true;
            setUser(user.get());
            // Navigate to landing page
            return "/index.xhtml";
        }
        this.authenticated = false;
        setUser(null);

        return "/views/loginFailed.xhtml";
    }


    public String logout() {
        user = null;
        this.authenticated = false;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        // Navigate back to main-page
        return "/index.xhtml?faces-redirect=true";
    }


    public boolean isAuthenticated() {
        try {
            this.authenticated = (boolean) getSession().getAttribute("authenticated");
        } catch (Exception e) {
            this.authenticated = false;
        }

        return authenticated;
    }

    public String updateProfile() {
        try {

            Optional<User> updatedUser = userService.getUserById((UUID) session.getAttribute("userId"));

            if (updatedUser.isEmpty()) return "/views/loginFailed.xhtml";

            if (getUpdateUsername() != null) {
                updatedUser.get().setUsername(getUpdateUsername());
            }

            if (getUpdateFirstName() != null) {
                updatedUser.get().setFirstname(getUpdateFirstName());
            }

            if (getUpdateLastName() != null) {
                updatedUser.get().setLastname(getUpdateLastName());
            }

            if (getUpdatePassword() != null && !getUpdatePassword().isEmpty()) {
                updatedUser.get().setPassword(getUpdatePassword());
            }

            if (getUpdateTheme() != null) {
                updatedUser.get().setTheme(getUpdateTheme());
            }

            userService.updateUser(updatedUser.get());
            return "/index.xhtml";
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }
}