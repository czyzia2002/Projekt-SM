package pl.edu.pb.restauracja.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import kotlinx.coroutines.channels.ActorKt;

@Entity
public class MenuItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String itemName;
    private String category;
    private double price;

    public MenuItem(){
    }

    public MenuItem(int id, String itemName, String category, double price){
        this.id = id;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
