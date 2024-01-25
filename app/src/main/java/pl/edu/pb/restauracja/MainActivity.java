package pl.edu.pb.restauracja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.Button;
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

        Button firstButton = findViewById(R.id.Restauracje);
        Button secondButton = findViewById(R.id.Zamow);
        Button thirdButton = findViewById(R.id.Menu);

        /*// Animacja dla przycisków
        Animation buttonAnimation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Obsługa orientacji poziomej (landscape)

            RelativeLayout.LayoutParams logoParams = (RelativeLayout.LayoutParams) logoImageView.getLayoutParams();
            logoParams.setMargins(0, 50, 0, 0);
            logoImageView.setLayoutParams(logoParams);

            logoImageView.startAnimation(logoAnimation);
            buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.landscape_button_slide_up);
            setStartOffsetForButton(firstButton, 0);
            setStartOffsetForButton(secondButton, 300);
            setStartOffsetForButton(thirdButton, 600);
        } else {
            // Obsługa orientacji pionowej
            buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_slide_up);
        }
        displayMenu(logoImageView,firstButton,secondButton, thirdButton,buttonAnimation);*/

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