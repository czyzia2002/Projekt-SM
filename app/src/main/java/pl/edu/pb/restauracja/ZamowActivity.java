package pl.edu.pb.restauracja;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.edu.pb.restauracja.database.Restaurant;
import pl.edu.pb.restauracja.database.RestaurantRepository;

public class ZamowActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zamow);

        List<Restaurant> restaurants = RestaurantRepository.getInstance().getRestaurants();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewRestaurants);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ZamowAdapter adapter = new ZamowAdapter(restaurants, this);
        recyclerView.setAdapter(adapter);
    }
}
