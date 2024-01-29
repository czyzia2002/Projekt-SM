package pl.edu.pb.restauracja.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Restaurant.class, MenuItem.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RestaurantDao restaurantDao();
    public abstract MenuDao menuDao();
}

