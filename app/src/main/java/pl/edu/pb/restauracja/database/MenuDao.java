package pl.edu.pb.restauracja.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MenuDao {
    @Insert
    void insertMenuItem(MenuItem menuItem);

    @Query("SELECT * FROM MenuItem")
    List<MenuItem> getAllMenuItems();
}