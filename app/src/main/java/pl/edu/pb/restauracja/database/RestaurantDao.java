package pl.edu.pb.restauracja.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RestaurantDao {
    @Insert
    void insertRestaurant(Restaurant restaurant);

    @Query("SELECT * FROM restaurant")
    List<Restaurant> getAllRestaurants();
    @Query("SELECT * FROM restaurant WHERE id = :id")
    Restaurant getRestaurantById(int id);
    @Query("UPDATE restaurant SET name = :name, city = :city, street = :street, number = :number WHERE id = :id")
    void updateRestaurant(int id, String name, String city, String street, String number);
    @Query("DELETE FROM restaurant WHERE id = :id")
    void deleteRestaurant(int id);

    //Inne metody
}
