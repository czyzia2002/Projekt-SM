package pl.edu.pb.restauracja;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.edu.pb.restauracja.database.AppDatabase;
import pl.edu.pb.restauracja.database.MenuDao;
import pl.edu.pb.restauracja.database.MenuItem;
import pl.edu.pb.restauracja.database.MenuRepository;
import pl.edu.pb.restauracja.database.Restaurant;
import pl.edu.pb.restauracja.database.RestaurantDao;

public class MenuActivity extends AppCompatActivity{
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

        AppDatabase appDatabase = DatabaseInstance.getInstance(this);

        // Uzyskaj instancję interfejsu dostępu do danych (DAO)
        MenuDao menuDao = appDatabase.menuDao();

        DatabaseInstance.insertInitialData2(menuDao);

        // Pobierz listę restauracji z bazy danych
        List<MenuItem> menuItems = menuDao.getAllMenuItems();

        Collections.sort(menuItems, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem item1, MenuItem item2) {
                return item1.getCategory().compareTo(item2.getCategory());
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recyclerView = findViewById(R.id.recyclerViewMenu);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MenuActivity.this);
                recyclerView.setLayoutManager(layoutManager);

                MenuAdapter adapter = new MenuAdapter(menuItems, MenuActivity.this);

                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        });
    }

}