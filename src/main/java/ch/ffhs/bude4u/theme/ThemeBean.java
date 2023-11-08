package ch.ffhs.bude4u.theme;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Named
@SessionScoped
public class ThemeBean implements Serializable {


    private String userTheme = "saga" ;
    private Map<String , String>  themeMap ;

    @PostConstruct
    public void init (){
        setThemeMapInit(  );
    }

    public void setUserTheme(String userTheme) {
        this.userTheme = userTheme;
    }

    public void setThemeMapInit() {
        themeMap =  new LinkedHashMap<String, String>();
        themeMap.put("Saga", "saga");
        themeMap.put("Vela", "vela");
        themeMap.put("Arya", "arya");
    }

    public void setThemeMap(Map<String, String> themeMap) {
        this.themeMap =themeMap;
    }


    public void  sumbitUserSettings (){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException ex) {
            Logger.getLogger(ThemeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getAvailableThemes() {
        return "Saga";
    }

    public String getCurrentTheme() {
        return "Saga";
    }

}
