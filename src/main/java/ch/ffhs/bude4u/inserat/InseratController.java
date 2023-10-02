package ch.ffhs.bude4u.inserat;

import ch.ffhs.bude4u.inserat.Inserat;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@RequestScoped
public class InseratController {
    private List<Inserat> inserate;

    public InseratController() {
        inserate = new ArrayList<>();
        inserate.add(new Inserat("T-Shirt", "blaues T-Shirt", "10.00", "shirt", "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", "2021-10-01", "aktiv", "1", "user1"));
        inserate.add(new Inserat("Hose", "blaue Hose", "20.00", "trouser", "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", "2021-10-02", "aktiv", "2", "user2"));
        inserate.add(new Inserat("Schuhe", "blaue Schuhe", "30.00", "shoes", "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", "2021-10-03", "aktiv", "3", "user3"));
        inserate.add(new Inserat("Hut", "blauer Hut", "40.00", "hat", "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", "2021-10-04", "aktiv", "4", "user4"));

        Inserat ins01 = new Inserat();
        ins01.setName("T-Shirt");
        ins01.setBeschreibung("blaues T-Shirt");
        ins01.setPreis("10.00");
        ins01.setKategorie("shirt");
        ins01.setBild("https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg");
        ins01.setDatum("2021-10-01");
        ins01.setStatus("aktiv");
        ins01.setId("1");
        ins01.setUser("user1");
        inserate.add(ins01);
    }

    public void setInserate(List<Inserat> inserate) {
        this.inserate = inserate;
    }

    public List<Inserat> getInserate() {
        return inserate;
    }

    public Inserat getInseratById(String id) {
        for (Inserat inserat : inserate) {
            if (inserat.getId().equals(id)) {
                return inserat;
            }
        }
        return null;
    }

    public Inserat updateInseratById (String id) {
        for (Inserat inserat : inserate) {
            if (inserat.getId().equals(id)) {
                return inserat;
            }
        }
        return null;
    }

    public void addInserat(Inserat inserat) {
        inserate.add(inserat);
    }

    public void removeInserat(Inserat inserat) {
        inserate.remove(inserat);
    }

    public String selectInserat(Inserat inserat) {
        return "advertisement.xhtml";
    }
}
