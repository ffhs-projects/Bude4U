package ch.ffhs.bude4u.navigation;

import ch.ffhs.bude4u.authentication.AuthenticationBean;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Named(value = "navigationController")
@RequestScoped
public class NavigationController implements Serializable {

    @Inject
    AuthenticationBean authBean;

    /**
     * -- GETTER --
     * Get if authenticated
     * return if authenticated
     */
    private boolean authenticated = false;

    /**
     * Shows the home page
     * @return Home page
     */
    public String showHome() {
        return "/index.xhtml";
    }

    /**
     * Shows the login page
     * @return Login page
     */
    public String showLogin() {
        return "/views/login.xhtml";
    }

    /**
     * Shows the logout page
     * @return Logout page
     */
    public String showLoginLogout() {
        return authBean.isAuthenticated() ? "/views/logout.xhtml" : "/views/login.xhtml";
    }

    /**
     * Shows the advertisement page
     * @return Advertisement page
     */
    public String userAdvertisements() {
        return "/views/userAdvertisement.xhtml";
    }

    /**
     * Shows the create advertisement page
     * @return Create advertisement page
     */
    public String createAdvertisement() {
        return "/views/createAdvertisement.xhtml";
    }

    /**
     * Shows the edit advertisement page
     * @return Edit advertisement page
     */
    public String editAdvertise() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String advertisementId = params.get("advertisementId");
        return "/views/editAdvertisement.xhtml?advertisement=" + advertisementId;
    }

    /**
     * Shows the show advertisement page
     * @return Show advertisement page
     */
    public String showAdvertisement() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String advertisementId = params.get("advertisementId");
        return "/views/showAdvertisement.xhtml?advertisement=" + advertisementId;
    }

    /**
     * Shows the register page
     * @return Register page
     */
    public String showRegister() {
        return "/views/register.xhtml";
    }

    /**
     * Shows the user profile page
     * @return User profile page
     */
    public String showUserProfile() {
        return "/views/userProfile.xhtml";
    }
}