package pl.edu.pb.restauracja.database;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlinx.coroutines.channels.ActorKt;

@Entity
public class MenuItem implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String itemName;
    private String category;
    private double price;
    private String ingredients;
    public MenuItem() {
    }

    public MenuItem(int id, String itemName, String category, double price, String[] ingredients) {
        this.id = id;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        Gson gson = new Gson();
        ArrayList<Ingredient> ingredient = new ArrayList<>();
        for(int i=0;i<ingredients.length;i++) {
            ingredient.add(new Ingredient(ingredients[i]));
        }
        this.ingredients = gson.toJson((ingredients));

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

    public String getIngredients(){
        return ingredients;
    }

    public String[] getIngredients2(){
        Gson gson = new Gson();
        return gson.fromJson(ingredients, String[].class);
    }
    public void setIngredients(String ingredients){
        Gson gson = new Gson();
        this.ingredients = gson.toJson(ingredients);
    }
    public void setIngredients2(Ingredient[] ingredients){
        Gson gson = new Gson();
        this.ingredients = gson.toJson(ingredients);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(itemName);
    }

    public static final Parcelable.Creator<MenuItem> CREATOR = new Parcelable.Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    private MenuItem(Parcel in) {
        itemName = in.readString();
    }
}
