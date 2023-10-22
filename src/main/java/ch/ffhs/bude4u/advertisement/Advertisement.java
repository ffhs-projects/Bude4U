package ch.ffhs.bude4u.advertisement;

import jakarta.annotation.ManagedBean;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Advertisement {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");
    LocalDateTime now = LocalDateTime.now();

    public Advertisement() {

    }


    public Advertisement(UUID advId, String title, String description, String date, String category, String status, double price, double rooms, int space, String mainPicUrl) {
        id = advId;
        advertisementTitle = title;
        mainDescription = description;
        creationDate = date;
        buyPrice = price;
        numberRooms = rooms;
        livingSpace = space;
        advCategory = category;
        advStatus = status;
        advertisementImages = new ArrayList<>();
        advertisementImages.add(mainPicUrl);
    }

    public Advertisement(String title, String description, String category, double price, double rooms, int space, String mainPicUrl) {
        id = UUID.randomUUID();
        advertisementTitle = title;
        mainDescription = description;
        creationDate = dtf.format(now);
        buyPrice = price;
        numberRooms = rooms;
        livingSpace = space;
        advCategory = category;
        advStatus = "offen";
        advertisementImages = new ArrayList<>();
        advertisementImages.add(mainPicUrl);
    }

    @Id
    UUID id;

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

    private ArrayList<String> advertisementImages;

    // Unique-Selling-Features (like Basement, Garage, Minergy...)
    private List<String> features;


    private UUID advertiserId;



    public String getMainImage() {
        return !advertisementImages.isEmpty() ? advertisementImages.get(0) : "";
    }

}
