package ch.ffhs.bude4u.advertisement;

import jakarta.annotation.ManagedBean;
import jakarta.persistence.*;
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
        return !advertisementImages.isEmpty() ? advertisementImages.get(0) : "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAHMAzAMBIgACEQEDEQH/xAAbAAEBAAMBAQEAAAAAAAAAAAAABQEDBAIGB//EADUQAQACAQICBQsDBAMAAAAAAAABAgMEEQWSFSExUVMSExQ0NVJyc6GxwTJBYSJxouEjgpH/xAAVAQEBAAAAAAAAAAAAAAAAAAAAAv/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/AP2EBSQAAAAAAAAAAAAAAAAAAAAAAAAAAYZAAAAAHLrNdj0u0WibXnrisOXpmngW5gVBL6Zp4FuY6Zp4FuYFQS+maeBbmOmaeBbmBUEvpmngW5jpmngW5gVBMjjOPeN8No/7KGLJTNjrkxzvW0bwD2AAAAAAAAAAAAAAACJrIi3Forbrib0jb/xUtpdLWJtbFjrWO2ZiEzVe2a/Mp+G7jl7RTHSP023mf5Btrbht7+RWMO/812dHomn8HHyvm30PDL2vosc233jeN5/cHv0TT+Bj5T0TT+Bj5W95veuOk3vaIrHbMg1eiafwMfKeiafwcfKxpdZj1MT5G8WjtrLoBP4jpsNNHktXFSLRttMRt+5wX1Wfjn7N3E/Usv8AaPu08F9Un45+0AoAAAAAAAAAAAAAAAAi6r2zX5lPwp6rTU1OLyL9W3XEx2wmar2zX5lPwtQCRXg9vL/rzR5P8R1quOlcWOtKRtWsbRDi4jr4wROPHtOWf8f9saLiNMmO0Z7RS9I3me8HdkvXHSb3mIrHbKDr9ZbVX6t6447K9/8AJrtbbVW2jqxR+mv5lyA948lsV4vjttaP3XtDrK6qndkj9VXzz3jyWxXi+OdrR2AvcT9Sy/2j7tPBfVJ+OftDOrvOXhU5JjabViWOCeqW+OftAKAAAAAAAAAAAAAAAAIuq9s1+ZT8O3iWrnS44rSP+S3ZM9kOLVe2a/Mp+HZxLR5NXOOcdqx5MTvuCHaZtMzM7zPbM/uwo9EZ/fx/U6Hz+/j+oJwo9EZ/fx/U6Hz+/j+oJwo9EZ/fx/U6Iz+/j+oOnN7Gr8urPBPVLfHP2hnV0nFwqcczEzWsROzHBfVLfHP2gFAAAAAAAAAAAAAAAAEriWkzTqPSNPE2nqmYjtiYafOcU7svLC2Ai+c4p3ZeSGPOcU93JyQtgIvnOKd2XkhjznFO7JyQtgIvnOKd2Xkg85xTuy8kLQCHkjiWavm8lck1nvrsp6DTzptPFLT/AFT1zt3ukAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB//2Q==";
    }

}
