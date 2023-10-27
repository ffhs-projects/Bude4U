package ch.ffhs.bude4u.authentication;

import ch.ffhs.bude4u.utils.PBKDF2Hash;
import jakarta.ejb.Stateless;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;

@Stateless
@Named
@Getter
@Setter
public class AuthenticationBean implements Serializable {

    @Inject
    private UserService userService;

    private HttpSession session = null;

    private boolean authenticated = false;
    private String username = null;
    private String password = null;
    private String firstName = null;
    private String lastName = null;

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
            return "ALREADY REGISTERED, NAVIGATE BACK TO LOGIN PAGE";
        }
        // Create new user
        User newuser = new User(firstName, lastName, username, password);
        userService.createUser(newuser);
        return "REGISTER_SUCCESSFULL, SWITCH BACK TO LOGIN PAGE";
    }

    public HttpSession getSession() {
        // if(session == null){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();

        return session;
    }

    public String login() {
        String userNameInput = getUsername();
        String passwordInput = getPassword();
        HttpSession session = getSession();
        Optional<User> user = userService.getUserByName(userNameInput);

        if (user.isEmpty()) return "USER NOT FOUND";

        String hashedPw = user.get().getPassword();
        boolean pwMatch = PBKDF2Hash.CheckPassword(hashedPw, passwordInput);

        if (pwMatch) {
            this.authenticated = true;
            setUser(user.get());
            return "SUCCESS_LOGIN";
        }
        this.authenticated = false;
        setUser(null);
        return "PW INVALID";
    }


    public String logout() {
        user = null;
        this.authenticated = false;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        return "SUCCESS_LOGOUT";
    }

    public boolean isAuthenticated() {
        try {
            this.authenticated = (boolean) getSession().getAttribute("authenticated");
        } catch (Exception e) {
            this.authenticated = false;
        }

        return authenticated;
    }
}