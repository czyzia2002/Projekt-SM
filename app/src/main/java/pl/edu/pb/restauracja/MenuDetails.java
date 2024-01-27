package pl.edu.pb.restauracja;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_details);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        double price = intent.getDoubleExtra("price", 0.0);

        TextView dishNameTextView = findViewById(R.id.textViewDishName);
        TextView dishPriceTextView = findViewById(R.id.textViewDishPrice);

        dishNameTextView.setText(itemName);
        dishPriceTextView.setText("Price: " + String.valueOf(price));
    }
}
