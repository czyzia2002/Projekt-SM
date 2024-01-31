package pl.edu.pb.restauracja.database;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    private static RestaurantRepository instance;
    private List<Restaurant> restaurants;



    public static synchronized RestaurantRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantRepository();
        }
        return instance;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
