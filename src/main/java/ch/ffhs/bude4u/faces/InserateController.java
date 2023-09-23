package ch.ffhs.bude4u.faces;

import ch.ffhs.bude4u.Inserat;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@Named
@RequestScoped
public class InserateController {
    private List<Inserat> inserate;

    public InserateController() {
        inserate = new ArrayList<>();
        inserate.add(new Inserat("T-Shirt", "blaues T-Shirt", "10.00", "shirt", "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", "2021-10-01", "aktiv", "1", "user1"));
        inserate.add(new Inserat("Hose", "blaue Hose", "20.00", "trouser", "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", "2021-10-02", "aktiv", "2", "user2"));
        inserate.add(new Inserat("Schuhe", "blaue Schuhe", "30.00", "shoes", "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", "2021-10-03", "aktiv", "3", "user3"));
        inserate.add(new Inserat("Hut", "blauer Hut", "40.00", "hat", "https://cdn.pixabay.com/photo/2016/11/18/17/46/house-1836070_1280.jpg", "2021-10-04", "aktiv", "4", "user4"));
    }
}
