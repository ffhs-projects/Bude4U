package ch.ffhs.bude4u.theme;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Named
@Getter
@SessionScoped
public class ThemeBean implements Serializable {


    @Setter
    private String userTheme = "saga";


    private Map<String, String> themeMap;

    /**
     * Initialize the theme map
     */
    @PostConstruct
    public void initializeThemeMap() {
        themeMap = new LinkedHashMap<>();
        themeMap.put("Saga", "saga");
        themeMap.put("Vela", "vela");
        themeMap.put("Arya", "arya");
    }

}
