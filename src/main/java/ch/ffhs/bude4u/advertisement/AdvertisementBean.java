package ch.ffhs.bude4u.advertisement;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Named
@SessionScoped
@Getter
@Setter
public class AdvertisementBean implements Serializable {
    @Inject
    private AdvertisementService advertisementService;
    private UUID advertisementId;
    private String advertisementTitle;
    private String mainDescription;
    private double buyPrice;
    private double numberRooms;
    private int livingSpace;
    private String creationDate;
    private String advCategory;
    private String advStatus;
    private int landArea;
    private String street;
    private String city;
    private int postalCode;
    private boolean test;
    Advertisement newAd;
    private HttpSession session = null;
    private ArrayList<String> advertisementImages = new ArrayList<>();
    private long filterPriceFrom = 0;
    private long filterPriceTo = 999999999;
    private double filterRoomFrom = 0;
    private double filterRoomTo = 50;
    private String filterCategory = "Any";
    private String filterCity = "Any";


    public HttpSession getSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();
        return session;
    }

    public String createAdvertisement() {
        session = getSession();
        try {
            // Required for unit tests
            if (test) {
                newAd = new Advertisement(advertisementTitle, mainDescription, "01.01.2001", advCategory, "offen", buyPrice, numberRooms, livingSpace, UUID.fromString(session.getAttribute("userId").toString()), street, city, postalCode, advertisementImages);
            } else {
                newAd = new Advertisement(advertisementTitle, mainDescription, advCategory, buyPrice, numberRooms, livingSpace, UUID.fromString(session.getAttribute("userId").toString()), street, city, postalCode, advertisementImages);
            }
            advertisementService.createAdvertisement(newAd);
            clearBean();
            return "/views/advertisement.xhtml?advertisement=" + newAd.getId() + "&faces-redirect=true";
        } catch (Exception e) {
            return "/views/advertisementFailed.xhtml";
        }
    }

    public String updateAdvertisement() {
        try {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            UUID adId = advertisementService.getAdvertisement(params.get("advertisement")).get().getId();
            Advertisement advertisement = advertisementService.getAdvertisement(adId.toString()).get();
            advertisement.setAdvertisementTitle(advertisement.getAdvertisementTitle());
            advertisement.setMainDescription(advertisement.getMainDescription());
            advertisement.setBuyPrice(advertisement.getBuyPrice());
            advertisement.setNumberRooms(advertisement.getNumberRooms());
            advertisement.setLivingSpace(advertisement.getLivingSpace());
            advertisement.setAdvCategory(advertisement.getAdvCategory());
            advertisement.setAdvStatus(advertisement.getAdvStatus());
            advertisement.setLandArea(advertisement.getLandArea());
            advertisement.setStreet(advertisement.getStreet());
            advertisement.setCity(advertisement.getCity());
            advertisement.setPostalCode(advertisement.getPostalCode());
            advertisement.setAdvertisementImages(advertisementImages);
            advertisementService.updateAdvertisement(advertisement);
            return "/views/advertisement.xhtml?advertisement=" + advertisement.getId() + "&faces-redirect=true";
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

    public String getFilterAdvertisement() {
        return "/index.xhtml";
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        UploadedFile file = event.getFile();

        String encodedString = Base64.getEncoder().encodeToString(file.getContent());

        advertisementImages.add(encodedString);
    }

    private void clearBean() {
        advertisementId = null;
        advertisementTitle = null;
        mainDescription = null;
        buyPrice = 0;
        numberRooms = 0;
        livingSpace = 0;
        creationDate = null;
        advCategory = null;
        advStatus = null;
        landArea = 0;
        street = null;
        city = null;
        postalCode = 0;
        test = false;
        HttpSession session = null;
        advertisementImages = new ArrayList<>();
    }
}

