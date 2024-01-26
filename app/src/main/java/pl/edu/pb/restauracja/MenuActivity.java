package pl.edu.pb.restauracja;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.edu.pb.restauracja.database.AppDatabase;
import pl.edu.pb.restauracja.database.MenuItem;
import pl.edu.pb.restauracja.database.MenuRepository;
import pl.edu.pb.restauracja.database.Restaurant;
import pl.edu.pb.restauracja.database.RestaurantRepository;

public class MenuActivity extends AppCompatActivity {
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "restaurant-database").build();

        displayMenu();
    }

    private void displayMenu() {

        List<MenuItem> menuItems = MenuRepository.getInstance().getMenuItems();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recyclerView = findViewById(R.id.recyclerViewMenu);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MenuActivity.this);
                recyclerView.setLayoutManager(layoutManager);

                MenuAdapter adapter = new MenuAdapter(menuItems);

                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
