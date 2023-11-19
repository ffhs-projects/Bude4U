package ch.ffhs.bude4u.advertisement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.Serializable;
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

public class Advertisement implements Serializable {
    @Transient
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    @Transient
    LocalDateTime now = LocalDateTime.now();

    @Id
    @GeneratedValue
    @Setter
    private UUID id;

    @Basic
    @Column(name = "advertisementTitle")
    private String title;

    @Basic
    @Column(name = "mainDescription")
    private String description;

    @Basic
    @Column(name = "creationDate")
    private String date;

    @Basic
    @Column(name = "buyPrice")
    private Double price;

    @Basic
    @Column(name = "numberRooms")
    private Double rooms;

    @Basic
    @Column(name = "livingSpace")
    private Integer space;

    @Basic
    @Column(name = "advCategory")
    private String category;

    @Basic
    @Column(name = "advStatus")
    private String status;

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

    public Advertisement() {
        advertisementImages = new ArrayList<>();
        advertiserId = UUID.randomUUID();
    }

    public Advertisement(String title, String description, String date, String category, String status, double price, double rooms, int space, UUID advUserId, String street, String city, Integer postalCode, List<String> images) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.price = price;
        this.rooms = rooms;
        this.space = space;
        this.category = category;
        this.status = status;
        advertiserId = advUserId;
        advertisementImages = new ArrayList<>();
        advertisementImages.addAll(images);
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;

    }

    public Advertisement(String title, String description, String category, double price, double rooms, int space, UUID advUserId, String street, String city, Integer plz, ArrayList<String> images) {
        this(title, description, "", category, "", price, rooms, space, advUserId, street, city, plz, images);
        this.date = dtf.format(now);
        this.status = "offen";
    }

    public String getMainImage() {
        return getImageWithIndex(0);
    }

    private String getImageWithIndex(Integer index) {
        try {
            if (advertisementImages.isEmpty()) throw new IndexOutOfBoundsException("No images available");
            if (advertisementImages.size() < index)  throw new IndexOutOfBoundsException("No images available");
            if (advertisementImages.get(index) == null) throw new FileNotFoundException("No Image Found");
            if (advertisementImages.get(index).startsWith("https://")) throw new IllegalArgumentException("Old Image File Format");
            return "data:image/png;base64," + advertisementImages.get(index);
        } catch (Exception ex) {
            return "data:image/png;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAHMAzAMBIgACEQEDEQH/xAAbAAEBAAMBAQEAAAAAAAAAAAAABQEDBAIGB//EADUQAQACAQICBQsDBAMAAAAAAAABAgMEEQWSFSExUVMSExQ0NVJyc6GxwTJBYSJxouEjgpH/xAAVAQEBAAAAAAAAAAAAAAAAAAAAAv/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/AP2EBSQAAAAAAAAAAAAAAAAAAAAAAAAAAYZAAAAAHLrNdj0u0WibXnrisOXpmngW5gVBL6Zp4FuY6Zp4FuYFQS+maeBbmOmaeBbmBUEvpmngW5jpmngW5gVBMjjOPeN8No/7KGLJTNjrkxzvW0bwD2AAAAAAAAAAAAAAACJrIi3Forbrib0jb/xUtpdLWJtbFjrWO2ZiEzVe2a/Mp+G7jl7RTHSP023mf5Btrbht7+RWMO/812dHomn8HHyvm30PDL2vosc233jeN5/cHv0TT+Bj5T0TT+Bj5W95veuOk3vaIrHbMg1eiafwMfKeiafwcfKxpdZj1MT5G8WjtrLoBP4jpsNNHktXFSLRttMRt+5wX1Wfjn7N3E/Usv8AaPu08F9Un45+0AoAAAAAAAAAAAAAAAAi6r2zX5lPwp6rTU1OLyL9W3XEx2wmar2zX5lPwtQCRXg9vL/rzR5P8R1quOlcWOtKRtWsbRDi4jr4wROPHtOWf8f9saLiNMmO0Z7RS9I3me8HdkvXHSb3mIrHbKDr9ZbVX6t6447K9/8AJrtbbVW2jqxR+mv5lyA948lsV4vjttaP3XtDrK6qndkj9VXzz3jyWxXi+OdrR2AvcT9Sy/2j7tPBfVJ+OftDOrvOXhU5JjabViWOCeqW+OftAKAAAAAAAAAAAAAAAAIuq9s1+ZT8O3iWrnS44rSP+S3ZM9kOLVe2a/Mp+HZxLR5NXOOcdqx5MTvuCHaZtMzM7zPbM/uwo9EZ/fx/U6Hz+/j+oJwo9EZ/fx/U6Hz+/j+oJwo9EZ/fx/U6Iz+/j+oOnN7Gr8urPBPVLfHP2hnV0nFwqcczEzWsROzHBfVLfHP2gFAAAAAAAAAAAAAAAAEriWkzTqPSNPE2nqmYjtiYafOcU7svLC2Ai+c4p3ZeSGPOcU93JyQtgIvnOKd2XkhjznFO7JyQtgIvnOKd2Xkg85xTuy8kLQCHkjiWavm8lck1nvrsp6DTzptPFLT/AFT1zt3ukAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB//2Q==";
        }
    }

}
