package ch.ffhs.bude4u.advertisement;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Entity
public class Advertisement {
    @Transient
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    @Transient
    LocalDateTime now = LocalDateTime.now();

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
        features = new ArrayList<>();
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
        features = new ArrayList<>();
    }

    @Id
    @Basic
    @Column(name = "id")
    private UUID id;
    @Basic
    @Column(name = "advertisementTitle")
    private String advertisementTitle;
    @Basic
    @Column(name = "mainDescription")
    private String mainDescription;
    @Basic
    @Column(name = "creationDate")
    private String creationDate;
    @Basic
    @Column(name = "buyPrice")
    private Double buyPrice;
    @Basic
    @Column(name = "numberRooms")
    private Double numberRooms;
    @Basic
    @Column(name = "livingSpace")
    private Integer livingSpace;
    @Basic
    @Column(name = "advCategory")
    private String advCategory;
    @Basic
    @Column(name = "advStatus")
    private String advStatus;
    @Basic
    @Column(name = "landArea")
    private Integer landArea;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "postalCode")
    private Integer postalCode;
    @Basic
    @Column(name = "advertisementImages")
    private ArrayList<String> advertisementImages;
    @Basic
    @Column(name = "advertiserId")
    private UUID advertiserId;
    @Basic
    private ArrayList<String> features;

    public Advertisement() {

    }

    public String getMainImage() {
        return !advertisementImages.isEmpty() ? advertisementImages.get(0) : "";
    }

}
