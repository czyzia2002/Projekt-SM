package pl.edu.pb.restauracja.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Restaurant {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String city;
    private String street;
    private String number;
    private double latitude;
    private double longitude;

    public Restaurant() {
    }

    public Restaurant(String name, String city, String street, String number, double latitude, double longitude) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.number = number;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter i setter dla name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter i setter dla city
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter i setter dla street
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    // Getter i setter dla number
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
