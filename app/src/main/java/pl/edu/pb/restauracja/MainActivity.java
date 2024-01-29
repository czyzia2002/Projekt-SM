package pl.edu.pb.restauracja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import pl.edu.pb.restauracja.database.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "restaurant-database").build();

        int orientation = getResources().getConfiguration().orientation;
        ImageView logoImageView = findViewById(R.id.logoImageView);
        logoImageView.setImageResource(R.drawable.logo);


        Button firstButton = findViewById(R.id.Restauracje);
        Button secondButton = findViewById(R.id.Zamow);
        Button thirdButton = findViewById(R.id.Menu);

        // Załaduj animacje
        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        Animation slideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in);

        // Ustaw animacje dla przycisków
        firstButton.startAnimation(fadeIn);
        secondButton.startAnimation(slideIn);
        thirdButton.startAnimation(slideIn);


        firstButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
            startActivity(intent);
        });

        secondButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ZamowActivity.class);
            startActivity(intent);
        });

        thirdButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        });
    }
}