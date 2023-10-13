package ch.ffhs.bude4u.advertisement;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

@Named
@RequestScoped
@Getter
@Setter
public class AdvertisementBean {
    @Inject
    private AdvertisementService advertisementService;
    private UUID advertisementId;
    private String advertisementTitle;
    private String mainDescription;
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
    private boolean test;
    Advertisement newAd;
    public String createAdvertisement() {
        try {
            // Required for unit tests
            if(test) {
                newAd = new Advertisement(advertisementId ,advertisementTitle, mainDescription,"01.01.2001", advCategory,"offen", buyPrice, numberRooms, livingSpace, mainPicUrl);
            } else {
                newAd = new Advertisement(advertisementTitle, mainDescription, advCategory, buyPrice, numberRooms, livingSpace, mainPicUrl);
           }
            advertisementService.createAdvertisement(newAd);
            return "/views/advertisement.xhtml?advertisement=" + newAd.getId() + "&faces-redirect=true";
        } catch (Exception e) {
            return "/views/advertisementFailed.xhtml";
        }
    }

    public String updateAdvertisement() {
        try {
            Advertisement advertisement = advertisementService.getAdvertisement(advertisementId).get();
            advertisement.setAdvertisementTitle(advertisementTitle);
            advertisement.setMainDescription(mainDescription);
            advertisement.setBuyPrice(buyPrice);
            advertisement.setNumberRooms(numberRooms);
            advertisement.setLivingSpace(livingSpace);
            advertisement.setAdvCategory(advCategory);
            advertisement.setAdvStatus(advStatus);
            advertisement.setLandArea(landArea);
            advertisement.setStreet(street);
            advertisement.setCity(city);
            advertisement.setPostalCode(postalCode);
            advertisement.setAdvertisementImages(new ArrayList<>());
            advertisement.getAdvertisementImages().add(mainPicUrl);
            advertisementService.updateAdvertisement(advertisement);
            return "/views/advertisement.xhtml?advertisement=" + advertisement.getId() + "&faces-redirect=true";
        } catch (Exception e) {
            return "/views/advertisementFailed.xhtml";
        }
    }
}

