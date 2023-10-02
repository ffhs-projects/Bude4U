package ch.ffhs.bude4u.advertisement;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Advertisement {

    public Advertisement(String id, String title, String description, String date, String category, String status, double price, double rooms, int space, String mainPicUrl) {
        id = id;
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

    String id;

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

    private String advertiserId;

    public String getMainImage() {
        return !advertisementImages.isEmpty() ? advertisementImages.get(0) : "";
    }

}
