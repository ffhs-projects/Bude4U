package ch.ffhs.bude4u.authentication;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;

@RequestScoped
@Named
public class AuthenticationController implements Serializable {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
    private User user;
    private boolean authenticated = false;
    private HttpSession session = null;
    private String userAgent;
    private UserService userService = null;


    public AuthenticationController() {
        this.userService = new UserService();
    }
    public HttpSession getSession() {
        // if(session == null){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();

        return session;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public String login() {
        String userNameInput = getUsername();
        String passwordInput = getPassword();
        HttpSession session = getSession();
        Optional<User> user = userService.getUserByName("a.b@c.d");

//        if (authResult) {
//            this.authenticated = true;
//
//            setUser(authenticationFacade.getUser());
//
//
//            return "SUCCESS_LOGIN";
//        } else {
//            this.authenticated = false;
//            setUser(null);
//            return "BAD_LOGIN";
//        }
return "huhu";
    }

    public String logout() {
        user = null;
        this.authenticated = false;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        return "SUCCESS_LOGOUT";
    }

    /**
     * @return the authenticated
     */
    public boolean isAuthenticated() {
        try {
            boolean auth = (Boolean) getSession().getAttribute("authenticated");
            if (auth) {
                this.authenticated = true;

            } else {
                authenticated = false;
            }
        } catch (Exception e) {
            this.authenticated = false;
        }

        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

}