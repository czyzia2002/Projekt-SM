package pl.edu.pb.restauracja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import java.util.Locale;

import pl.edu.pb.restauracja.database.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.VISIBLE);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Pobierz aktualny język
        String currentLanguage = Locale.getDefault().getLanguage();

        // Ukryj opcje zmiany języka, zależnie od bieżącego języka
        MenuItem changeToEnglishItem = menu.findItem(R.id.action_change_language_to_english);
        MenuItem changeToPolishItem = menu.findItem(R.id.action_change_language_to_polish);

        if (currentLanguage.equals("en")) {
            changeToEnglishItem.setVisible(false);
        } else {
            changeToPolishItem.setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_change_language_to_english) {
            setLocale("en");
            return true;
        } else if (itemId == R.id.action_change_language_to_polish) {
            setLocale("pl");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Tutaj możesz dodać ponowne ładowanie komponentów interfejsu użytkownika, jeśli jest to wymagane
        recreate();
    }
}
