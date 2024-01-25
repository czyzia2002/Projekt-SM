package pl.edu.pb.restauracja.database;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    private static RestaurantRepository instance;
    private List<Restaurant> restaurants;

    private RestaurantRepository() {
        restaurants = new ArrayList<>();
        // Dodawanie restauracji
        restaurants.add(new Restaurant("Zwierzyniec", "Białystok", "Zwierzniecka", "6", 53.11966936655787, 23.150193796527187));
        restaurants.add(new Restaurant("Obok Rynku", "Bielsk Podlaski", "Mickiewicza", "49", 52.77197652449937, 23.193009153588086));
        restaurants.add(new Restaurant("Centrum", "Białystok", "Sienkiewicza", "3", 53.13281011504806, 23.160434781185547));
        restaurants.add(new Restaurant("Zielone Wzgórza", "Białystok", "Wrocławska", "45", 53.12446534452306, 23.095771011870312));
    }

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
