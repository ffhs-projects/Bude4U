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
    private String title;
    private String description;
    private Double price;
    private Double rooms;
    private Integer space;
    private String date;
    private String category;
    private String status;
    private Integer landArea;
    private String street;
    private String city;
    private Integer postalCode;
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
    @Getter
    private ArrayList<String> roomsList = new ArrayList<>();
    // Category list
    @Getter
    private ArrayList<String> categoryList = new ArrayList<>();

    /**
     * Constructor
     */
    public AdvertisementBean() {

        // Fill roomsList
        for (double i = 0; i <= 12; i+=0.5) {
            this.roomsList.add(String.valueOf(i));
        }

        // Fill categoryList
        categoryList.add("Beliebig");
        categoryList.add("Wohnung");
        categoryList.add("Haus");
        categoryList.add("Hobbyraum");
        categoryList.add("Garagenplatz");
        categoryList.add("Lager");
        categoryList.add("Andere");

    }


    /**
     * Get all advertisements
     *
     * @return List of advertisements
     */
    public HttpSession getSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        session = request.getSession();
        return session;
    }

    /**
     * Create a new advertisement
     * @return Redirect to user advertisement page
     */
    public String createAdvertisement() {
        session = getSession();
        try {
            // Required for unit tests
            if (test) {
                newAd = new Advertisement(title, description, "01.01.2001", category, "offen", price, rooms, space, UUID.fromString(session.getAttribute("userId").toString()), street, city, postalCode, advertisementImages);
            } else {
                newAd = new Advertisement(title, description, category, price, rooms, space, UUID.fromString(session.getAttribute("userId").toString()), street, city, postalCode, advertisementImages);
            }
            advertisementService.createAdvertisement(newAd);
            clearBean();
            return "/views/userAdvertisement.xhtml?faces-redirect=true";
        } catch (Exception e) {
            return "/views/failedAdvertisement.xhtml";
        }
    }

    /**
     * Update an advertisement
     * @return Redirect to show advertisement page
     */
    public String updateAdvertisement() {
        try {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            UUID adId = advertisementService.getAdvertisement(params.get("advertisement")).get().getId();
            Advertisement advertisement = advertisementService.getAdvertisement(adId.toString()).get();
            advertisement.setTitle(advertisement.getTitle());
            advertisement.setDescription(advertisement.getDescription());
            advertisement.setPrice(advertisement.getPrice());
            advertisement.setRooms(advertisement.getRooms());
            advertisement.setSpace(advertisement.getSpace());
            advertisement.setCategory(advertisement.getCategory());
            advertisement.setStatus(advertisement.getStatus());
            advertisement.setLandArea(advertisement.getLandArea());
            advertisement.setStreet(advertisement.getStreet());
            advertisement.setCity(advertisement.getCity());
            advertisement.setPostalCode(advertisement.getPostalCode());
            advertisement.setAdvertisementImages(advertisementImages);
            advertisementService.updateAdvertisement(advertisement);
            return "/views/showAdvertisement.xhtml?advertisement=" + advertisement.getId() + "&faces-redirect=true";
        } catch (Exception e) {
            return "/views/failedAdvertisement.xhtml";
        }
    }

    /**
     * Delete an advertisement
     * @return Redirect to user advertisement page
     */
    public String deleteAdvertisement() {
        try {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String paramID = params.get("advertisement");
            if (paramID != null) {
                advertisementService.delete(UUID.fromString(paramID));
                return "/views/userAdvertisement.xhtml?faces-redirect=true";
            } else {
                // Handle the case where "paramID" is null
                return "/views/failedAdvertisement.xhtml";
            }
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            return "/views/failedAdvertisement.xhtml";
        }
    }

    /**
     * Get an advertisement
     * @return Redirect to show advertisement page
     */
    public String getFilterAdvertisement() {
        return "/index.xhtml";
    }


    /**
     * Handle file upload
     * @param event File upload event
     */
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        UploadedFile file = event.getFile();

        String encodedString = Base64.getEncoder().encodeToString(file.getContent());

        advertisementImages.add(encodedString);
    }

    /**
     * Clear the bean
     */
    private void clearBean() {
        advertisementId = null;
        title = null;
        description = null;
        price = null;
        rooms = null;
        space = null;
        date = null;
        category = null;
        status = null;
        landArea = null;
        street = null;
        city = null;
        postalCode = 0;
        test = false;
        advertisementImages = new ArrayList<>();
    }

}

