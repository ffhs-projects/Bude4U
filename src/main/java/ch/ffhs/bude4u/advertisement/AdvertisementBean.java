package ch.ffhs.bude4u.advertisement;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Named
@RequestScoped
@Getter
@Setter
public class AdvertisementBean {
    @Inject
    private AdvertisementService advertisementService;

    @Id
    private UUID advertisementId = UUID.randomUUID();
    private String advertisementTitle;
    private String mainDescription;
    private String creationDate;
    private double buyPrice;
    private double numberRooms;
    private int livingSpace;
    // TODO: Wechsel auf Enum für Filter
    private String advCategory;
    private String advStatus;
    private int landArea;
    private String street;
    private String city;
    private int postalCode;
    private String mainPicUrl;
    private String status = "offen";

    public String createAdvertisement() {
        try {
            Advertisement newAd = new Advertisement(advertisementId, advertisementTitle, mainDescription, creationDate, advCategory, status, buyPrice, numberRooms, livingSpace, mainPicUrl);
            advertisementService.createAdvertisement(newAd);
            return "/views/advertisement.xhtml?advertisement=" + advertisementId + "&faces-redirect=true";
        } catch (Exception e) {
            return "/views/fail.xhtml";
        }
    }
}
