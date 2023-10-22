package ch.ffhs.bude4u.advertisement;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;
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
    private String creationDate;
    // TODO: Wechsel auf Enum für Filter
    private String advCategory;
    private String advStatus;
    private int landArea;
    private String street;
    private String city;
    private int postalCode;
    private String mainPicUrl;
    private boolean test;

    public String createAdvertisement() {
        Advertisement newAd;
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
        Advertisement updateAd;
        try {
            Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            UUID adId = advertisementService.getAdvertisement(params.get("advertisement")).get().id;


            updateAd = new Advertisement(adId ,advertisementTitle, mainDescription,creationDate, advCategory,advStatus, buyPrice, numberRooms, livingSpace, mainPicUrl);


/*            advertisement.setAdvertisementTitle(advertisementTitle == null ? advertisement.getAdvertisementTitle() : advertisementTitle);
            advertisement.setMainDescription(mainDescription == null ? advertisement.getMainDescription() : mainDescription);
            advertisement.setBuyPrice(buyPrice == 0 ? advertisement.getBuyPrice() : buyPrice);
            advertisement.setNumberRooms(numberRooms == 0 ? advertisement.getNumberRooms() : numberRooms);
            advertisement.setLivingSpace(livingSpace == 0 ? advertisement.getLivingSpace() : livingSpace);
            advertisement.setAdvCategory(advCategory == null ? advertisement.getAdvCategory() : advCategory);
            advertisement.setAdvStatus(advStatus == null ? advertisement.getAdvStatus() : advStatus);
            advertisement.setLandArea(landArea == 0 ? advertisement.getLandArea() : landArea);
            advertisement.setStreet(street == null ? advertisement.getStreet() : street);
            advertisement.setCity(city == null ? advertisement.getCity() : city);
            advertisement.setPostalCode(postalCode == 0 ? advertisement.getPostalCode() : postalCode);
            advertisement.setAdvertisementImages(new ArrayList<>());
            advertisement.getAdvertisementImages().add(mainPicUrl);*/
            advertisementService.updateAdvertisement(updateAd);
            return "/views/advertisement.xhtml?advertisement=" + updateAd.getId() + "&faces-redirect=true";
        } catch (Exception e) {
            return "/views/advertisementFailed.xhtml";
        }
    }

    public String deleteAdvertisement() {
        try {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String paramID = params.get("advertisement");
            if (paramID != null) {
                advertisementService.delete(UUID.fromString(paramID));
                return "/index.xhtml?faces-redirect=true";
            } else {
                // Handle the case where "paramID" is null
                return "/views/advertisementFailed.xhtml";
            }
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            return "/views/advertisementFailed.xhtml";
        }
    }
}

