package pl.edu.pb.restauracja.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Restaurant.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RestaurantDao restaurantDao();
}

