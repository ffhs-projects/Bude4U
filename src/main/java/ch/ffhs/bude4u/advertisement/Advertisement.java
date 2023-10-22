package ch.ffhs.bude4u.advertisement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "advertisement")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Advertisement {
    @Transient
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    @Transient
    LocalDateTime now = LocalDateTime.now();

    public Advertisement() {
        advertiserId = UUID.randomUUID();
    }

    public Advertisement(String title, String description, String date, String category, String status, double price, double rooms, int space, String mainPicUrl) {
        advertisementTitle = title;
        mainDescription = description;
        creationDate = date;
        buyPrice = price;
        numberRooms = rooms;
        livingSpace = space;
        advCategory = category;
        advStatus = status;
        advertiserId = UUID.randomUUID();
//        advertisementImages = mainPicUrl;
        // TODO: Add list for images
        advertisementImages = new ArrayList<>();
//        advertisementImages.add("1");
//        advertisementImages.add("2");
        advertisementImages.add(mainPicUrl);
    }

    public Advertisement(String title, String description, String category, double price, double rooms, int space, String mainPicUrl) {
        advertisementTitle = title;
        mainDescription = description;
        creationDate = dtf.format(now);
        buyPrice = price;
        numberRooms = rooms;
        livingSpace = space;
        advCategory = category;
        advStatus = "offen";
        advertiserId = UUID.randomUUID();
//        advertisementImages = mainPicUrl;

        advertisementImages = new ArrayList<>();
//        advertisementImages.add("1");
//        advertisementImages.add("2");
        advertisementImages.add(mainPicUrl);
//        features = new ArrayList<>();
//        features.add("1");
//        features.add("2");
    }

    @Id
    @GeneratedValue
    @Setter
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

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "bude_images", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "advertisementImage")
    private List<String> advertisementImages;

    @Basic
    @Column(name = "advertiserId")
    private UUID advertiserId;

    public String getMainImage() {
        return advertisementImages.isEmpty() ? "" : advertisementImages.get(0);
    }

}
