package ch.ffhs.bude4u.navigation;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Getter
@Named(value = "navigationController")
@RequestScoped
public class NavigationController implements Serializable{

    /**
     * -- GETTER --
     *
     * @return the authenticated
     */
    private boolean authenticated = false;

    public String showHome(){
        return "/index.xhtml";
    }

    public String showLogin(){
        return "/views/login.xhtml";
    }

    public String createAdvertisement(){
        return "/views/advertise.xhtml";
    }

    public String editAdvertise(){
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String advertisementId = params.get("advertisementId");
        return "/views/advertisementEdit.xhtml?advertisement=" + advertisementId;
    }

    public String showAdvertisement(){
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String advertisementId = params.get("advertisementId");
        return "/views/advertisement.xhtml?advertisement=" + advertisementId;
    }


    //TODO: Demonstrates the use of conditional navigation
    public void login(){
        // Perform some tasks, if needed, and then return boolean
        setAuthenticated(true);
        System.out.println("Here");
    }

    /**
     * @param authenticated the authenticated to set
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}