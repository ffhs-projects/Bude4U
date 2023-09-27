package ch.ffhs.bude4u.advertisement;

import java.util.Dictionary;
import java.util.List;

public class Advertisement {

    String id;

    private String advertisementTitle;

    private String mainDescription;

    private double buyPrice;

    private float numberRooms;

    private int livingSpace;

    private int landArea;

    private String street;

    private String city;

    private int postalCode;

    // Key: picture-name, Value: picture-url
    private Dictionary<String, String> advertisementImages;

    // Unique-Selling-Features (like Basement, Garage, Minergy...)
    private List<String> features;

    private String advertiserId;

    // TODO: Add additional necessary Properties, accessor, ctor, etc...

}
