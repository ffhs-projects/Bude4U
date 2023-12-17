package ch.ffhs.bude4u.theme;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Named
@SessionScoped
public class ThemeBean implements Serializable {

    private HttpSession session = null;

    @Getter
    @Setter
    private String userTheme = "saga";

    @Getter
    private Map<String, String> themeMap;


    @PostConstruct
    public void initializeThemeMap() {
        themeMap = new LinkedHashMap<>();
        themeMap.put("Saga", "saga");
        themeMap.put("Vela", "vela");
        themeMap.put("Arya", "arya");
    }

    /**
     * Set the selected theme in the session
     *
     * @return String
     */
    public String getSelectedUserTheme() {
        session = getSession();
        String sessionTheme = (String) session.getAttribute("selectedTheme");
        if (sessionTheme == null || sessionTheme.trim().isEmpty()) return "saga";
        return sessionTheme;
    }

    /**
     * Set the selected theme in the session
     *
     * @return String
     */
    public HttpSession getSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();

        return session;
    }

}
